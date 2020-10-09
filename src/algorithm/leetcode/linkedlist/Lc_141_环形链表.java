package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc_141_环形链表 {

    // 环形链表
    // https://leetcode-cn.com/problems/linked-list-cycle/

    public static void main(String[] args) {
        Lc_141_环形链表 lc = new Lc_141_环形链表();
        ListNode head = new ListNode(3);
        lc.linkedListInitialize(head);    // head = [3,2,0,-4], pos = 1

        System.out.println("链表 " + lc.linkedListToString(head) + " 中" + (lc.hasCycle_Point(head) ? "存在" : "不存在") + "环.");
    }

    // 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean hasCycle_Hash(ListNode head) {
        Set<ListNode> seen = new HashSet<>();

        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    // Floyd - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean hasCycle_Point(ListNode head) {
        ListNode slow = head, fast = head;

        do {
            if (fast == null || fast.next == null) return false;

            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        return true;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
    }

    private String linkedListToString(ListNode head) {
        if (head == null) return "[]";

        StringBuffer buf = new StringBuffer("[");
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) {
                break;
            }
            buf.append(head.val).append(",");
            head = head.next;
        }

        return buf.deleteCharAt(buf.length() - 1).append("]").toString();
    }
}
