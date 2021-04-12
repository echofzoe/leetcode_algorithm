## LongAdder

- 统计器类，是用作在***高并发情况下实现高性能统计***的类

- 在`jdk1.8`之前，用`AtomicLong`在高并发的情况下统计数据，但`AtomicLong`存在一些问题

  - `AtomicLong`的底层是调用`c++`实现的`native CAS`方法来实现的，而`native CAS`是调用`cpu`的`cmpxchg`指令来完成`CAS`的
  - 在高并发情况下，`native CAS`对于多个线程竞争一个共享资源的情况，每次只能让一个线程竞争成功，而其他线程会一直自旋等待，占用`cpu`时间
  - 故`AtomicLong`在高并发情况下的性能很差，`jdk1.8`后，便加入了`LongAdder`来替代他

- 流程：

  ![LongAdder](.\static\LongAdder.jpg)

- `LongAdder`并不含有`base,cell`等字段，它们是从`Striped64`继承来的

  - `Striped64`中的字段：

    ```java
    // Table of cells. When non-null, size is a power of 2.
    transient volatile Cell[] cells;
    
    // Base value, used mainly when there is no contention, but also as a fallback during table initialization races. Updated via CAS.
    // 没有发生竞争，数据会累加到 base | cells 扩容时，需要将数据写回 base
    transient volatile long base;
    
    // Spinlock (locked via CAS) used when resizing and/or creating Cells.
    // 初始化 cells 或扩容 cells 都需要获取锁
    // 0 - 无锁 | 1 - 其他线程已经持有这把锁
    transient volatile int cellsBusy;
    
    // Number of CPUS, to place bound on table size
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    
    
    // Padded variant of AtomicLong supporting only raw accesses plus CAS. JVM intrinsics note: It would be possible to use a release-only form of CAS here, if it were provided.
    @sun.misc.Contended static final class Cell {
        volatile long value;
        Cell(long x) { value = x; }
        final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }
    
        // Unsafe mechanics
        private static final sun.misc.Unsafe UNSAFE;
        // valueOffset - value 字段基于当前对象内存的地址偏移
        private static final long valueOffset;
        static {
            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> ak = Cell.class;
                valueOffset = UNSAFE.objectFieldOffset
                    (ak.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }
    
    // CASes the base field.
    // 通过 CAS 方式写 base
    final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
    }
    
    // CASes the cellsBusy field from 0 to 1 to acquire lock.
    // 通过 CAS 方式获取锁
    final boolean casCellsBusy() {
        return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
    }
    
    // Returns the probe value for the current thread. Duplicated from ThreadLocalRandom because of packaging restrictions.
    // 获取当前线程的哈希值
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }
    
    // Pseudo-randomly advances and records the given probe value for the given thread. Duplicated from ThreadLocalRandom because of packaging restrictions.
    // 重置当前线程的 hash 值
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }
    ```

  - `Striped64`的核心方法`longAccumulate`：

    ```java
    // Handles cases of updates involving initialization, resizing, creating new Cells, and/or contention. See above for explanation. This method suffers the usual non-modularity problems of optimistic retry code, relying on rechecked sets of reads.
    // x - 增量
    // fn - 更新函数，或者置 null 表示添加（此约定避免了 LongAdder 中需要额外的字段或函数）
    // wasUncontended - 只有 cells 初始化之后，当前线程竞争 cell 时 CAS 失败，才置 false，说明发生竞争
    final void longAccumulate(long x, LongBinaryOperator fn,
                                  boolean wasUncontended) {
        // 进入到此方法的条件
        // 1. cells 未初始化，即多线程写 base 发生了竞争 [初始化 cells]    --- 进入 case2
        // 2. 当前线程对应下标的 cell 为空，需要创建 [去创建 cell 并写入]  --- 进入 case1.1
        // 3. cell 有竞争，CAS 失败 [重试 | 扩容]   --- 进入 case1.3 重试 | 进入 case1.5 后自旋再进入 case1.6 就执行扩容
        
        int h;  // 线程的 hash 值
        
        // condition
        // - true -- 当前线程还未被分配 hash 值
        // - false -- 反之
        if ((h = getProbe()) == 0) {
            // 给当前线程分配 hash 值
            ThreadLocalRandom.current(); // force initialization
            h = getProbe();
            
            // 默认情况下，当前线程 hash 值为 0，会写入到 cells[0]
            // 故 jdk 认为这次"竞争"不是一次真正的竞争，因为线程在分配 hash 后会映射到其他 cell
            wasUncontended = true;
        }
        
        // 是否哈希碰撞
        // - true -- 可能会扩容
        // - false -- 没有哈希碰撞，一定不会扩容
        boolean collide = false;                // True if last slot nonempty
        
        // 自旋
        for (;;) {
            // as - cells 引用
            // a - 当前线程命中的 cell
            // n - cells 数组长度
            // v - 期望值
            Cell[] as; Cell a; int n; long v;
            
            // case1
            // - true -- cells 已经被初始化了 [写 cell]
            // - false -- 反之
            if ((as = cells) != null && (n = as.length) > 0) {
                
                // case1.1
                // - true -- 当前线程对应下标的 cell 为空，需要 new Cell(x)
                // - false -- 反之
                if ((a = as[(n - 1) & h]) == null) {
                    // 当前锁未被占用
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(x);   // Optimistically create
                        
                        // 再次检测锁状态，并尝试获取锁
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;  // 是否创建成功的标志位
                            try {               // Recheck under lock
                                // rs - cells 引用
                                // m - cells 数组长度
                                // j - 当前线程命中的 cell 下标
                                Cell[] rs; int m, j;
                                
                                // condition1 & condition2 恒成立
                                // condition3 - rs[j = (m - 1) & h] == null ? 防止其他线程已初始化该位置，当前线程再次初始化时导致丢失数据
                                // - true -- 可以新建 cell
                                // - false -- 已有其他线程抢先新建了一个 cell 并放入
                                if ((rs = cells) != null &&
                                    (m = rs.length) > 0 &&
                                    rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    
                    // 当前锁已被占用 - 不进行扩容，因为当前 as 为空，还没有尝试写入
                    collide = false;
                }
                
                // case1.2
                // - 只有 cells 初始化之后，当前线程竞争 cell 时 CAS 失败，才置 false，说明发生竞争
                // - true -- 发生竞争
                // - false -- 未发生竞争
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                
                // case1.3
                // - 前置条件 -- 当前线程 rehash 过，新命中的 cell 不为空
                // - true -- 写成功，退出自旋
                // - false -- 表示 rehash 后命中的新 cell 也有竞争，会进入 case1.4 重试一次，再失败，进入 case1.5 再重试一次
                else if (a.cas(v = a.value, ((fn == null) ? v + x :
                                             fn.applyAsLong(v, x))))
                    break;
                
                // case1.4
                // condition1 - n >= NCPU ?
                // - true -- 将扩容意向改为 false [当前线程不扩容了]
                // - false -- 表示 cells 还可以扩容
                // condition2 - cells != as ?
                // - true -- 其他线程已经扩容过，当前线程 rehash 后重试即可 [当前线程不扩容了]
                // - false -- cells 还可以扩容，并且当前 cells 也没有被其他线程扩容过
                else if (n >= NCPU || cells != as)
                    collide = false;            // At max size or stale
                
                // case1.5
                // - true -- 即 collide = false，设置扩容意向为 true，但不一定真的发生扩容
                // - false -- 即 collide = true，扩容意向已为 true，继续重试
                else if (!collide)
                    collide = true;
                
                // case1.6 - 真正扩容的逻辑
                // condition1 - cellsBusy == 0 ?
                // - true -- cells 无锁 [当前线程竞争锁]
                // - false -- cells 已加锁 [rehash 后自旋]
                // condition2 - casCellsBusy() ?
                // - true -- 竞争锁成功，可以进行扩容，注意这里仅仅是可以
                // - false -- 竞争锁失败，当前时刻有其他线程正在执行扩容相关操作
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        // 再次检车 cells，防止被其他线程更改后，当前线程再次更改导致丢失数据
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];  // 扩容一倍
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];  // 转移数据
                            cells = rs;  // 更改引用
                        }
                    } finally {
                        cellsBusy = 0;  // 释放锁
                    }
                    // 扩容意向置 false，使用已扩展的 cells 继续重试
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                
                // rehash
                h = advanceProbe(h);
            }
            
            // case2
            // - 前置条件 -- cells 未被初始化，as 为 null
            // condition1 - cellsBusy == 0 ? 是否加锁
            // - true -- 未加锁
            // - false -- 加锁 
            // condition2 - cells == as ? 再一次判断
            // - true -- cells = as = null
            // - false -- 其他线程在当前线程给 as 赋值后修改了 cells，导致 cells != null = as
            // condition3 - casCellsBusy() ? 是否成功获取锁
            // - true -- 获取锁成功，cellsBusy 置 1
            // - false -- 获取锁失败，cellsBusy 置 0
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    // 再一次判断，防止如下的情况发生：
                    // 两个线程t1,t2同时在 else if 入口处满足条件1和条件2，
                    // 而t1首先拿到锁，执行完整个初始化方法，然后退出，释放锁
                    // 接着t2拿到锁，开始执行，
                    // 如果在这里不再一次判断，则t2又会初始化一遍 cells，造成t1的 cells 数据丢失
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            
            // case3
            // - 前置条件
            // -- 1. cellsBusy = 1，表其他线程正在初始化 cells [当前线程需累加数据至 base]
            // -- 2. cells 已经被其他线程初始化 [当前线程需累加数据至 base]
            // -- 3. CAS 锁失败 [当前线程需累加数据至 base]
            else if (casBase(v = base, ((fn == null) ? v + x :
                                        fn.applyAsLong(v, x))))
                break;                          // Fall back on using base
        }
    }
    ```

- `add`方法

  ```java
  // Adds the given value.
  // Params: x – the value to add
  public void add(long x) {
      // as - cells 引用
      // b - 获取的 base 值
      // v - 期望值
      // m - cells 数组长度，为 2 的幂
      // a - 当前线程命中的 cell 单元
      Cell[] as; long b, v; int m; Cell a;
      
      // condition1
      // - true - 说明 cells 已被初始化 [写 cell]
      // - false - 说明 cells 未初始化 [写 base]
      // condition2
      // - false - 说明当前线程使用 CAS 替换数据成功
      // - true - 说明发生了竞争 [重试 | 扩容]
      if ((as = cells) != null || !casBase(b = base, b + x)) {
          // 进入此循环的条件
          // 1. cells 已被初始化
          // 2. 写 base 失败
          
          boolean uncontended = true;  // true - 未发生竞争，false - 反之
          
          // condition1 & condition2
          // - true -- cells 未初始化，即多线程写 base 发生了竞争
          // - false -- cells 已经初始化 [写 cell]
          // condition3 - getProbe() 获取当前线程的哈希值
          // - true -- 当前线程对应的 cell 为空 [创建 cell]
          // - false -- 当前线程对应的 cell 不空 [写 cell]
          // condition4
          // - false -- 当前线程写 cell 成功，置 uncontended 为 true，返回
          // - true -- 说明写 cell 有竞争，置 uncontended 为 false，进循环
          if (as == null || (m = as.length - 1) < 0 ||
              (a = as[getProbe() & m]) == null ||
              !(uncontended = a.cas(v = a.value, v + x)))
              // 进入此循环的条件
              // 1. cells 未初始化，即多线程写 base 发生了竞争 [初始化 cells]
              // 2. 当前线程对应的 cell 为空，需要创建 [去创建 cell 并写入]
              // 3. cell 有竞争，CAS 失败 [重试 | 扩容]
              
              // 执行 Striped64 类的 longAccumulate 方法
              longAccumulate(x, null, uncontended);
      }
  }
  ```

- `sum`方法

  ```java
  // Resets variables maintaining the sum to zero. This method may be a useful alternative to creating a new adder, but is only effective if there are no concurrent updates. Because this method is intrinsically racy, it should only be used when it is known that no threads are concurrently updating.
  // 累加 cells 中和 base 中的数据并返回
  public long sum() {
      Cell[] as = cells; Cell a;
      long sum = base;
      if (as != null) {
          for (int i = 0; i < as.length; ++i) {
              if ((a = as[i]) != null)
                  sum += a.value;
          }
      }
      return sum;
  }
  ```


