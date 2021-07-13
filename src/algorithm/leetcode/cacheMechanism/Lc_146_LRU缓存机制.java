package algorithm.leetcode.cacheMechanism;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 * <P>https://leetcode-cn.com/problems/lru-cache/</P>
 *
 * @author echofzoe
 * @since 2021.7.13
 */
public class Lc_146_LRU缓存机制 {

    public static void main(String[] args) {
        Lc_146_LRU缓存机制 lc = new Lc_146_LRU缓存机制();

        LRUCache lRUCache  = new LRUCache(2);
        lRUCache.put(1, 1);                  // 缓存是 {1=1}
        lRUCache.put(2, 2);                  // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1)); // 返回 1
        lRUCache.put(3, 3);                  // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2)); // 返回 -1 (未找到)
        lRUCache.put(4, 4);                  // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1)); // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3)); // 返回 3
        System.out.println(lRUCache.get(4)); // 返回 4
    }

}

// LRU Customization - 时间复杂度 O(1) put&get - 空间复杂度 O(capacity)
class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> m;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (m.containsKey(key)) {
            Node node = m.get(key);

            moveToHead(node);

            return node.val;
        }

        return -1;
    }

    public void put(int key, int val) {
        if (get(key) > -1) {
            m.get(key).val = val;
        } else {
            if (m.size() == capacity) {
                int tpk = tail.prev.key;
                removeLast();
                m.remove(tpk);
            }

            Node node = new Node(key, val);
            m.put(key, node);
            addToHead(node);
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void removeLast() {
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }

    static class Node{
        int key, val;
        Node prev, next;

        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }
}
