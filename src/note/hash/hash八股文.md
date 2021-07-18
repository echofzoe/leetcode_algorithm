### ConcurrentHashMap 在 JDK1.7 和 JDK1.8 中的不同

- 底层结构不同：
  - `JDK1.7`中，底层是用`分段数组 Segment + 散列数组 HashEntry + 链表（HashEntry 节点）`实现的，其中`Segment`继承于`ReentrantLock`、是一把分段锁
  - `JDK1.8`中，底层是用`synchronized + cas + Node（HashEntry） + 链表（可树化）`实现的
- 因底层结构不同，锁的粒度也不同：
  - `JDK1.7`中，锁的粒度是基于`Segment`的，一个`Segment`可能包含多个`HashEntry`
  - `JDK1.8`中，锁的粒度直接就是`Node`级别
  - `JDK1.8`为什么使用内置锁`synchronized`来代替`ReentrantLock`？
    - 在粗粒度的加锁方式中，`ReentrantLock`可以通过内部类`Condition`来控制各个低粒度的边界，更加灵活，而在低粒度中，`Condition`就没有优势了；那么对于低粒度的加锁方式，在`JDK1.6`引入了自旋锁和偏向锁的`synchronized`性能不比`ReentrantLock`差
    - `synchronized`是`JVM`层面上实现的关键字，它的使用比`API`更加自然
    - 在大量数据的操作下，对于`JVM`的内存压力，基于`API`的`ReentrantLock`会开销更多的内存
- `HashEntry`中存放数据的策略不同：
  - `JDK1.7`中，`HashEntry`只能存放链表
  - `JDK1.8`中，`Node`上的链表在元素数量超过树化阈值并且总元素数超过阈值后，会树化成`TreeNode（红黑树）`，优化查询效率
- `JDK1.8`中基于`LongAdder`来实现高并发情况下的高性能元素计数