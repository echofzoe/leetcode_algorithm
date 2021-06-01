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

