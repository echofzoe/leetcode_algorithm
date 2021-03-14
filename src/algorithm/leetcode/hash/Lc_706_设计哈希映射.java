package algorithm.leetcode.hash;

import java.util.Iterator;
import java.util.LinkedList;

public class Lc_706_设计哈希映射 {

    // 设计哈希映射
    // https://leetcode-cn.com/problems/design-hashmap/

    public static void main(String[] args) {
        Lc_706_设计哈希映射 lc = new Lc_706_设计哈希映射();
        MyHashMap map = new MyHashMap();

        map.put(1, 1);
        System.out.println("向哈希映射中添加(1,1)");
        map.put(2,2);
        System.out.println("向哈希映射中添加(2,2)");
        System.out.println("哈希映射中1的映射为" + map.get(1));
        System.out.println("哈希映射中3的映射为" + map.get(3));
        map.put(2,1);
        System.out.println("向哈希映射中添加(2,1)");
        System.out.println("哈希映射中2的映射为" + map.get(2));
        map.remove(2);
        System.out.println("向哈希映射中删除键2");
        System.out.println("哈希映射中2的映射为" + map.get(2));
    }

}

// 拉链法 - 时间复杂度 O(N/b) 其中N为哈希表中的元素数量，b为链表的数量。假设哈希值是均匀分布的，则每个链表大概长度为N/b - 空间复杂度 O(N + b)
class MyHashMap {

    private final int BUCKET = 1009;
    private final LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.data = new LinkedList[BUCKET];
        for (int i = 0; i < BUCKET; i++) {
            data[i] = new LinkedList<>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int pos = hash(key);
        Iterator<Integer[]> ite = data[pos].iterator();
        while (ite.hasNext()) {
            Integer[] element = ite.next();
            if (element[0] == key) {
                element[1] = value;
                return;
            }
        }
        data[pos].offerLast(new Integer[]{key, value});
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map get no mapping for the key */
    public int get(int key) {
        int pos = hash(key);
        Iterator<Integer[]> ite = data[pos].iterator();
        while (ite.hasNext()) {
            Integer[] element = ite.next();
            if (element[0] == key) {
                return element[1];
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map get a mapping for the key */
    public void remove(int key) {
        int pos = hash(key);
        Iterator<Integer[]> ite = data[pos].iterator();
        while (ite.hasNext()) {
            Integer[] element = ite.next();
            if (element[0] == key) {
                data[pos].remove(element);
                return;
            }
        }
    }

    private int hash(int key) {
        return key % BUCKET;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */


