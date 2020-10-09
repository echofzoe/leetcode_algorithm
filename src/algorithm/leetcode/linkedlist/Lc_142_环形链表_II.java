package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lc_142_环形链表_II {

    // 环形链表 II
    // https://leetcode-cn.com/problems/linked-list-cycle-ii/

    public static void main(String[] args) {
        Lc_142_环形链表_II lc = new Lc_142_环形链表_II();
        ListNode head = new ListNode(3);
        lc.linkedListInitialize(head);    // head = [3,2,0,-4], pos = 1
        
        if (lc.detectCycle_Hash(head) != null) {
            System.out.println("tail connects to node index " + lc.pos);
        } else {
            System.out.println("no cycle");
        }

        System.out.println(lc.detectCycle_Point(head).val);
    }

    int pos = -1;

    // 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public ListNode detectCycle_Hash(ListNode head) {
        Map<ListNode, Integer> seen = new HashMap<>();
        pos = 0;

        while (head != null) {
            if (seen.containsKey(head)) {
                pos = seen.get(head);
                return head;
            }
            seen.put(head, pos++);
            head = head.next;
        }

        return null;
    }

    // Floyd - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode detectCycle_Point(ListNode head) {
        ListNode slow = head, fast = head;

        do {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // 相遇后，找环入口
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
    }
}
