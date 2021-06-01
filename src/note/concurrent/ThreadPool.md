## 线程池

### 概述

#### 1. 设计理念

1. 降低资源消耗
   - 通过重复利用已创建的线程来降低由线程创建和销毁造成的资源消耗
   - 线程的创建与销毁需要操作系统介入，届时`os`会由用户态切换到内核态来完成相关操作
   - *线程池通过复用`corePoolSize`个线程来执行不同的任务*
2. 提高响应速度
   - 当任务到达时，池中如有空闲线程，则任务不必等待线程创建就能立即执行
3. 方便线程管理
   - 线程是稀缺资源，由用户无限制的创建显然是不合适的
   - 使用线程池可以对池中线程进行统一的分配、调优与监控，方便管理

#### 2. 设计思路

![线程池的设计思路](..\assets\statics\线程池的设计思路.png)

#### 3. 框架体系

![线程池Executor框架体系](..\assets\statics\线程池Executor框架体系.png)

- 线程池的真正实现类是`ThreadPoolExecutor`

### 构造方法

#### 源码

```java
// 在基础的构造方法之上，使用默认线程工厂和默认拒绝策略
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         Executors.defaultThreadFactory(), defaultHandler);
}

// 在基础的构造方法之上，使用默认拒绝策略但要指定 线程工厂
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         threadFactory, defaultHandler);
}

// 在基础的构造方法之上，使用默认线程工厂但要指定 拒绝策略
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          RejectedExecutionHandler handler) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         Executors.defaultThreadFactory(), handler);
}

// 基础的构造方法，根据给定的初始参数创建一个新的 ThreadPoolExecutor，被其它构造方法内部调用
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

#### 参数说明

- **`corePoolSize（必需）：`**核心线程数
  - 池中一直保持存活的线程数，即使这些线程处于空闲状态
  - 当`allowCoreThreadTimeOut`参数为`true`时，核心线程处于空闲状态的时间超过`keepAliveTime`后，也会被回收
- **`maximumPoolSize（必需）：`**池中允许的线程数上限
  - 当核心线程全部繁忙且任务队列也满了的时候，线程池会临时追加线程，直到总数达到这个上限
- **`keepAliveTime（必需）：`**线程空闲时间上限
  - 当非核心线程处于空闲状态的时间超过这个上限后，该线程会被回收
  - `allowCoreThreadTimeOut`参数为`true`时，核心线程超时也会被回收
- **`unit（必需）：`**`keepAliveTime`参数的时间单位
  - 可选`TimeUnit.DAYS`、`TimeUnit.HOURS`、`TimeUnit.MINUTES`、`TimeUnit.SECONDS`等
- **`workQueue（必需）：`**任务队列，采用阻塞队列实现
  - 当核心线程全部繁忙时，后续由`execute()`方法提交的`Runnable`将存放在任务队列中，等待被线程处理
- **`threadFactory（可选）：`**线程工厂，指定线程池创建线程的方式
- **`handler（可选）：`**拒绝策略
  - 当线程池中线程数量达到`maximumPoolSize`且`workQueue`也满了的时候，后续提交的任务将被拒绝
  - `handler`可以指定用什么方式拒绝任务，共有`4`种拒绝策略

### 拒绝策略

- **`AbortPolicy（缺省）：`**直接丢弃任务并抛出`RejectedExecutionException`异常
- **`CallerRunsPolicy：`**直接运行任务的`run()`方法，但并非是由线程池的线程处理，而是交由任务的调用线程处理
- **`DiscardPolicy：`**直接丢弃任务，不抛出任何异常
- **`DiscardOldestPolicy：`**将当前处于等待队列队头的等待任务强行取出，再试图将当前被拒绝的任务提交到线程池执行

### 默认线程工厂

- 一般不建议使用`DefaultThreadFactory`，因为它生成的线程名为线程编号，很含糊，业务出问题时不好定位

```java
private static class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
        Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
            poolNumber.getAndIncrement() +
            "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                              namePrefix + threadNumber.getAndIncrement(),
                              0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
```

### 任务队列

- 使用`ThreadPoolExecutor`需要指定一个实现了`BlockingQueue`接口的任务等待队列
- `ThreadPoolExecutor`的`API`文档中一共推荐了三种等待队列：
  - **`SynchronousQueue:`**同步队列
    - 每个插入操作都必须等待另一个线程执行相应的移除操作，反之亦然
    - 队列内部无任何容量
    - 无法查看同步队列，因为元素仅在您尝试删除它时才存在
  - **`LinkedBlockingQueue:`**基于链表的可选界限的阻塞双端队列（默认容量是`Integer.MAX_VALUE`，即无界）
    - 使用无界队列后，当核心线程都繁忙时，后续任务可以无限加入此队列，因此线程池中的线程数不会超过核心线程数，也就是说`maximumPoolSize = corePoolSize`
    - 无界队列可以提高线程池的吞吐量，但代价是牺牲内存空间，甚至会导致内存溢出
    - 使用时可以指定容量，使之成为一种有界队列
  - **`ArrayBlockingQueue:`**基于数组的有界阻塞队列
    - 在线程池初始化时需要指定队列的容量，并且容量后续无法再调整
    - 这种有界队列有利于防止资源耗尽，但可能更难调整和控制
- 此外，`JAVA`还提供了`4`种队列：
  - **`PriorityBlockingQueue:`**支持优先级排序的无界阻塞队列
    - 存放在`PriorityBlockingQueue`中的元素必须实现`Comparable`接口，这样才能通过实现`compareTo()`方法进行排序
    - 优先级最高的元素将始终排在队列的头部
    - `PriorityBlockingQueue`不能保证优先级一样的元素间的排序，也不保证当前队列中除了优先级最高的元素以外的元素间的排序
  - **`DelayQueue:`**基于二叉堆的延迟队列，同时具备无界队列、阻塞队列、优先队列的特征
    - 队列中必须存放实现了`Delayed`接口的类对象
    - 队列通过执行延时来提取任务，时间没到，任务就取不出来
  - **`LinkedBlockingDeque:`**基于链表的双端阻塞队列
  - **`LinkedTransferQueue:`**基于链表的无界阻塞队列
    - 这个队列比较特别的时，采用一种预占模式，意思就是消费者线程取元素时，如果队列不为空，则直接取走数据，若队列为空，那就生成一个节点（节点元素为`null`）入队，然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素

### 线程池状态

- 线程池状态通过`ctl`的高`3`位表示
- 线程池共有`5`种状态
  - **`RUNNING:`**当创建线程池后，初始时，线程池处于运行态
  - **`SHUTDOWN:`**如果调用了`shutdown()`方法，则线程池处于`SHUTDOWN`状态，此时线程池不能够接受新的任务，但会等待所有任务执行完毕
  - **`STOP:`**如果调用了`shutdownNow()`方法，则线程池处于`STOP`状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务
  - **`TIDYING:`**当所有的任务已终止，`ctl`记录的任务数量为`0`，线程池会变为`TIDYING`状态
    - 当线程池变为`TIDYING`状态时，会执行钩子函数`terminated()`。`terminated()`在`ThreadPoolExecutor`类中是空的，若用户想在线程池变为`TIDYING`时，进行相应的处理，可以通过重载`terminated()`函数来实现
    - 当线程池在`SHUTDOWN`状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 `SHUTDOWN -> TIDYING`
    - 当线程池在`STOP`状态下，线程池中执行的任务为空时，就会由`STOP -> TIDYING`
  - **`TERMINATED:`**当线程池处于`SHUTDOWN`或`STOP`状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为`TERMINATED`状态

### 源码分析

#### ThreadPoolExecutor

##### - 属性1

```java
// 高3位，表示当前线程池的运行状态；除去高3位之后的低位，表示当前线程池中所拥有的线程数量
private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
// 表示在 ctl 中，低 COUNT_BITS 位是用于存放当前线程数量的位，这里是 32 - 3 = 29 位
private static final int COUNT_BITS = Integer.SIZE - 3;
// CAPACITY，表示低 COUNT_BITS 位所能表达的最大数值，为 000 11111111111111111111111111111
private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

// runState is stored in the high-order bits
// 111 00000000000000000000000000000 (负数)
private static final int RUNNING    = -1 << COUNT_BITS;
// 000 00000000000000000000000000000
private static final int SHUTDOWN   =  0 << COUNT_BITS;
// 001 00000000000000000000000000000
private static final int STOP       =  1 << COUNT_BITS;
// 010 00000000000000000000000000000
private static final int TIDYING    =  2 << COUNT_BITS;
// 011 00000000000000000000000000000
private static final int TERMINATED =  3 << COUNT_BITS;
```

##### - 方法

```java
// Packing and unpacking ctl
// 获取当前线程池的运行状态
private static int runStateOf(int c)     { return c & ~CAPACITY; }
// 获取当前线程池的线程数量
private static int workerCountOf(int c)  { return c & CAPACITY; }
// 用于重置当前线程池的 ctl 值，rs 表线程池状态，wc 表线程池中 worker(线程) 数量
private static int ctlOf(int rs, int wc) { return rs | wc; }

// 比较当前线程池 ctl 所表示的状态 c，是否小于某个状态 s
private static boolean runStateLessThan(int c, int s) { return c < s; }
// 比较当前线程池 ctl 所表示的状态 c，是否大于等于某个状态 s
private static boolean runStateAtLeast(int c, int s) { return c >= s; }
// 判断当前线程池 ctl 所表示的状态 c，是否是 RUNNING，因为只有 RUNNING(负数) < SHUTDOWN(0)
private static boolean isRunning(int c) { return c < SHUTDOWN; }


// CAS-increment for ctl
private boolean compareAndIncrementWorkerCount(int expect) {
    return ctl.compareAndSet(expect, expect + 1);
}

// CAS-decrement for ctl
private boolean compareAndDecrementWorkerCount(int expect) {
    return ctl.compareAndSet(expect, expect - 1);
}

// 将 ctl 值 -1，这个方法一定成功(底层一直 do...while... 直到成功)
private void decrementWorkerCount() { ctl.addAndGet(-1); }
```

##### - 属性2

```java
// 任务队列，当线程池中的线程达到核心线程数量时，再提交任务，就会直接提交到 workQueue
private final BlockingQueue<Runnable> workQueue;
// 线程池全局锁(增加、减少 worker、修改线程池运行状态时需要持有 mainLock)
private final ReentrantLock mainLock = new ReentrantLock();
// 线程池中真正存放 worker 的地方
private final HashSet<Worker> workers = new HashSet<>();
// 当外部线程调用 awaitTermination() 方法时，外部线程会等待当前线程池状态为 Termination 为止
// - 等待 就是将外部线程封装成 waitNode 放入到 Condition 队列中
// - waitNode.Thread 就是外部线程，会被 park 掉(处于 WAITING 状态)
// - 当线程池状态变为 Termination 时，会通过 termination.signalAll() 去唤醒这些线程
// - 唤醒之后的线程会进入阻塞队列，然后头结点会去抢占 mainLock
// - 抢占到的线程，会继续执行 awitTermination() 后面的程序
private final Condition termination = mainLock.newCondition();

// 记录线程池生命周期内的 线程数最大值
private int largestPoolSize;
// 记录线程池所完成的 任务总数(当 worker 退出时，累加已完成的任务数)
private long completedTaskCount;

// 创建线程时使用的线程工厂
// - 当我们使用 Executors.newFix...newCache... 创建线程池时，使用的是 DefaultThreadFactory
// - 一般不建议使用 DefaultThreadFactory(因为它生成的线程名为线程编号，很含糊，业务出问题时不好定位)
// - 推荐自己实现 ThreadFactory
private volatile ThreadFactory threadFactory;
// 拒绝策略，JUC 包中提供了 4 种方式(具体如下列出)，默认采用 AbortPolicy 抛出异常
// - AbortPolicy 抛出异常
// - CallerRunsPolicy 调用者线程执行
// - DiscardOldestPolicy 丢弃队列中最老的任务，再次尝试提交
// - DiscardPolicy 直接丢弃
private volatile RejectedExecutionHandler handler;

// 空闲线程存活时间
// - 当 allowCoreThreadTimeOut == false 时，会维护核心线程数量内的线程存活，超出部分的线程会被超时回收
// - 当 allowCoreThreadTimeOut == true 时，核心线程数量内的线程空闲时也会被回收
private volatile long keepAliveTime;
// 控制核心线程数量内的线程是否可以被回收
private volatile boolean allowCoreThreadTimeOut;
// 核心线程数量限制
private volatile int corePoolSize;
// 线程池最大线程数量限制
// - the effective limit is maximumPoolSize & COUNT_MASK
private volatile int maximumPoolSize;

// 缺省的拒绝策略
private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
```

##### - 内部类

```java
// worker 采用了 AQS 的独占模式
// 独占模式有两个重要属性：state 和 ExclusiveOwnerThread
// - state：0 时表示未被占用(可抢锁)，>0 时表示被占用(加锁了)，<0 时表示初始状态(此时不能被抢锁)
// - ExclusiveOwnerThread：表示独占锁的线程
private final class Worker
    extends AbstractQueuedSynchronizer
    implements Runnable
{
    /**
     * This class will never be serialized, but we provide a
     * serialVersionUID to suppress a javac warning.
     */
    private static final long serialVersionUID = 6138294804551838833L;

    @SuppressWarnings("serial") // Unlikely to be serializable
    // worker 内部封装的工作线程(Null if factory fails)
    final Thread thread;
    
    @SuppressWarnings("serial") // Not statically typed as Serializable
    // Initial task，假设 firstTask 不为空(Possibly null)
    // - 那么当 worker 启动后(内部的线程启动)会优先执行 firstTask
    // - 当 firstTask 执行完成后，会到 queue 中取获取下一个任务
    Runnable firstTask;
    
    // 记录当前线程所完成的任务数量
    volatile long completedTasks;

    // TODO: switch to AbstractQueuedLongSynchronizer and move
    // completedTasks into the lock word.

    // 构造方法，firstTask 可以为 null
    // - firstTask 为 null 时，启动后会到 queue 中获取
    Worker(Runnable firstTask) {
        // 设置 AQS 独占模式为初始化状态，此时不能被抢锁
        setState(-1); // inhibit interrupts until runWorker
        this.firstTask = firstTask;
        // 使用线程工厂创建了一个线程，并且将当前 worker 指定为 Runnable
        // 也就是说当 thread 启动时，会以 worker.run() 为入口
        this.thread = getThreadFactory().newThread(this);
    }
    
    // 当 worker 启动时，会
    public void run() { runWorker(this); }

    // 判断挡圈 worker 的独占锁是否被占用
    // - The value 0 represents the unlocked state.
    // - The value 1 represents the locked state.
    protected boolean isHeldExclusively() { return getState() != 0; }

    // 尝试去占用 worker 的独占锁
    protected boolean tryAcquire(int unused) {
        // 使用 CAS 修改 AQS 中的 state，期望值为 0(未占用)，欲更新为 1(当前线程抢占锁成功)
        if (compareAndSetState(0, 1)) {
            // 设置 ExclusiveOwnerThread 为当前线程
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    // 尝试去释放锁
    // - 外部不会直接调用这个方法，此方法是 AQS 内调用的
    // - 外部调用 unlock() 时，会经由 unlock() -> AQS.release() -> tryRelease() 来调用
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    // 加锁，失败时会阻塞当前线程，直到获取到锁的位置
    public void lock()        { acquire(1); }
    // 尝试去加锁，失败时直接返回 false，不会阻塞当前线程
    public boolean tryLock()  { return tryAcquire(1); }
    // 一般情况下，调用 unlock() 要保证当前线程是持有锁的
    // 特殊情况下，当 worker 的 states == -1 时，调用 unlock() 表示初始化 state 并设置 state = 0
    // - 故启动 worker 之前会先调用 unlock() 方法，强制刷新 ExclusiveOwnerThread = null 与 state = 0
    public void unlock()      { release(1); }
    // 返回当前 worker 的独占锁是否被占用
    public boolean isLocked() { return isHeldExclusively(); }

    void interruptIfStarted() {
        Thread t;
        if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
            try {
                t.interrupt();
            } catch (SecurityException ignore) {
            }
        }
    }
}
```

