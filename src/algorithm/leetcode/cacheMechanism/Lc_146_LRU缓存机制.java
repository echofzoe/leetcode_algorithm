package algorithm.leetcode.cacheMechanism;

import java.util.HashMap;
import java.util.Map;

public class Lc_146_LRU缓存机制 {

    // LRU缓存机制
    // https://leetcode-cn.com/problems/lru-cache/

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

    private class DeLinkedNode {
        int key;
        int value;
        DeLinkedNode prev;
        DeLinkedNode next;
        public DeLinkedNode() { }
        public DeLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DeLinkedNode> cache = new HashMap<>();
    private int size;
    private final int capacity;
    private DeLinkedNode head, tail;    // dummy

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DeLinkedNode();
        tail = new DeLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DeLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DeLinkedNode node = cache.get(key);
        if (node == null) {
            DeLinkedNode newNode = new DeLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            if (size > capacity) {
                DeLinkedNode last = removeTail();
                cache.remove(last.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(DeLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DeLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DeLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DeLinkedNode removeTail() {
        DeLinkedNode last = tail.prev;
        removeNode(last);
        return last;
    }

}
