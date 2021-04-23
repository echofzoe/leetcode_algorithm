## HASH

- `HASH`：也称散列、哈希，基本原理是把任意长度的输入，通过`hash`算法变成固定长度的输出

- 特点：

  - 从`hash`值不可以反向推导出原始的数据
  - 输入数据的微小变化会得到完全不同的`hash`值，相同的数据会得到相同的值

- `JDK8`中的`hash`部分集合继承与实现结构

  ![HASH](..\assets\statics\HASH.jpg)

### HashMap

#### - 哈希扰动算法

- 让`K`的`hashcode`的高`16`位也参与哈希运算，使得哈希值更加散列

- 此算法的目的是解决当哈希表的长度较小时，`K`的`hashcode`和`table.length - 1`进行`&`运算时，`table.length - 1`的高位全为`0`，对于路由算法没有意义

  ```java
  static final int hash(Object key) {
      int h;
      return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }
  ```

#### - 路由算法

- 位与`&`操作计算当前`hash`值在哈希表中的索引

  ```java
  int n = table.length;
  int index = table[(n - 1) & hash];
  ```

#### - 常量 & 阈值 & 字段

```java
// 常量字段
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;  // aka 16 缺省的table大小
static final int MAXIMUM_CAPACITY = 1 << 30;         // table的最大容量
static final float DEFAULT_LOAD_FACTOR = 0.75f;      // 缺省的负载因子

// 阈值
// - 树化阈值 - 树化阈值1与2都满足时才会树化
static final int TREEIFY_THRESHOLD = 8;              // 树化阈值1 - 桶位上链表长度 >= 8
static final int MIN_TREEIFY_CAPACITY = 64;          // 树化阈值2 - 节点总数 >= 64
// - 反树化阈值（链化）
static final int UNTREEIFY_THRESHOLD = 6;            // 桶位上树节点数 <= 6
// 扩容阈值
int threshold;                                       // threshold = capacity * loadFactor
final float loadFactor;                              // 负载因子

// 字段
transient Node<K,V>[] table;                         // 哈希表, 延时初始化
transient Set<Map.Entry<K,V>> entrySet;              // 保存缓存的 entrySet
transient int size;                                  // 当前哈希表中的元素个数
transient int modCount;                              // 当前哈希表中的结构修改（替换不是结构修改）次数
```

#### - 源码

##### -- 构造方法

```java
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}

public HashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
}

public HashMap(int initialCapacity, float loadFactor) {
    // 校验 capacity
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    
    // 校验 loadFactor
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    
    // 赋值
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);   // 2的幂
}

public HashMap(Map<? extends K, ? extends V> m) {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    putMapEntries(m, false);
}
```

##### -- 初始化指定哈希表大小的细节

- 当初始化时指定了哈希表大小、而这个大小又不是`2`的幂时，会通过`tableSizeFor`函数将此大小改变为比其大的`2`的幂

  ```java
  /**
   * Returns a power of two size for the given target capacity.
   */
  static final int tableSizeFor(int cap) {
      int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
      return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
  ```


##### -- put 方法流程

- 当执行一个`map.put(K, V)`操作后：
  1. 程序首先会获取到`K`的`hash`值
  2. 再经过哈希扰动函数（让哈希值的高`16`位也参与运算）使`hash`值更散列
  3. 之后构造出`Node`对象
  4. 最后经过路由算法（由位运算实现的取模算法）找到对应的桶位，在此桶位上进行进一步的保存元素操作

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

@Override
public V putIfAbsent(K key, V value) {
    return putVal(hash(key), key, value, true, true);
}

/**
 * Implements Map.put and related methods.
 *
 * @param hash hash for key
 * @param key the key
 * @param value the value to put
 * @param onlyIfAbsent if true, don't change existing value
 * @param evict if false, the table is in creation mode.
 * @return previous value, or null if none
 */
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    // tab - 当前散列表的引用
    // p - 当前散列表寻址找到的桶位上的元素
    // n - 当前散列表数组的长度
    // i - 路由寻址的结果
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    
    // case1 - 散列表还未初始化
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;  // 延迟初始化
    
    // case1 - p 为空，直接将当前的(K, V)扔进去就可以了
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    
    // case2 - p 已经存在
    else {
        // e - node 临时元素
        // k - p 的键
        Node<K,V> e; K k;
        
        // case1 - p 的 key 与待插入的 key 相等, 需要替换 p 的旧值
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;  // 置 e 为 p
        
        // case2 - p 的 key 与待插入元素的 key 不同
        // 以 p 为头结点的链表已经树化
        else if (p instanceof TreeNode)
            // 去红黑树上查是否有与待插入元素相同的 key
            // 查到了就置 e 为 p, 没查到就插入到红黑树中
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        
        // 以 p 为头结点的链表没有树化
        else {
            // 去链表上查是否有与待插入元素相同的 key
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    // 没查到, 将待插入元素尾插                    
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                // 查到了, 退出循环, 等待后面将 e 的旧值替换
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        
        // 散列表中存在一个与待插入元素相同 key 的元素 e, 替换 e 的旧值为待插入的新值 
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    
    ++modCount;  // 散列表结构被修改次数+1
    
    if (++size > threshold)
        // 插入新元素后 size 超过阈值, 则扩容
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

https://www.bilibili.com/video/BV1LJ411W7dP?p=6&spm_id_from=pageDriver

##### -- 静态内部类 Node<K, V>

```java
/**
 * Basic hash bin node, used for most entries.  (See below for
 * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
 */
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
```

- 