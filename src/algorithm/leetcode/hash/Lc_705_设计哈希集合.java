package algorithm.leetcode.hash;

import java.util.Iterator;
import java.util.LinkedList;

public class Lc_705_设计哈希集合 {

    // 设计哈希集合
    // https://leetcode-cn.com/problems/design-hashset/

    public static void main(String[] args) {
        Lc_705_设计哈希集合 lc = new Lc_705_设计哈希集合();
        MyHashSet2 set = new MyHashSet2();

        set.add(1);
        System.out.println("向哈希集合中添加1");
        set.add(2);
        System.out.println("向哈希集合中添加2");
        System.out.println("哈希集合中" + (set.contains(1) ? "包含1" : "不包含1"));
        System.out.println("哈希集合中" + (set.contains(3) ? "包含3" : "不包含3"));
        set.add(1000000);
        System.out.println("向哈希集合中添加100000");
        System.out.println("哈希集合中" + (set.contains(1000000) ? "包含1000000" : "不包含1000000"));
        set.remove(1000000);
        System.out.println("向哈希集合中删除100000");
        System.out.println("哈希集合中" + (set.contains(1000000) ? "包含1000000" : "不包含1000000"));
    }

}

// 拉链法 - 时间复杂度 O(N/b) 其中N为哈希表中的元素数量，b为链表的数量。假设哈希值是均匀分布的，则每个链表大概长度为N/b - 空间复杂度 O(N + b)
class MyHashSet1 {
    // 使用素数做桶，方便散列
    private final int BUCKET = 1009;
    private final LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet1() {
        this.data = new LinkedList[BUCKET];
        for (int i = 0; i < BUCKET; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int pos = hash(key);

        Iterator<Integer> ite = data[pos].iterator();
        if (ite.hasNext()) {
            Integer element = ite.next();
            if (element == key) {
                return;
            }
        }

        data[pos].offerLast(key);
    }

    public void remove(int key) {
        int pos = hash(key);

        Iterator<Integer> ite = data[pos].iterator();
        while (ite.hasNext()) {
            Integer element = ite.next();
            if (element == key) {
                data[pos].remove(element);
                return;
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int pos = hash(key);

        Iterator<Integer> ite = data[pos].iterator();
        while (ite.hasNext()) {
            Integer element = ite.next();
            return element == key;
        }

        return false;
    }

    private int hash(int key) {
        return key % BUCKET;
    }
}

// bitmap - 时间复杂度 O(1) - 空间复杂度 O(N)
class MyHashSet2 {

    private int[] mask;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet2() {
        this.mask = new int[1000000 / 32 + 1];
    }

    public void add(int key) {
        setKey(key, true);
    }

    public void remove(int key) {
        setKey(key, false);
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return getKey(key);
    }

    private void setKey(int key, boolean flag) {
        int idx = key / 32, bitIdx = key % 32;
        if (flag) {
            // 增
            mask[idx] |= (1 << bitIdx);
        } else {
            // 删
            mask[idx] &= ~(1 << bitIdx);
        }
    }

    private boolean getKey(int key) {
        int idx = key / 32, bitIdx = key % 32;

        return (mask[idx] >> bitIdx & 1) == 1;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
