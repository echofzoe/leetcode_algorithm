package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_61_旋转链表 {

    // 旋转链表
    // https://leetcode-cn.com/problems/rotate-list/

    public static void main(String[] args) {
        Lc_61_旋转链表 lc = new Lc_61_旋转链表();
        ListNode head = new ListNode(1);
        lc.linkedListInitialize(head);    // head = [1,2,3,4,5]
        int k = 2;

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 中每个节点向右移动 " + k + " 个位置后的结果为 " + ListNodeSerialize.serialize(lc.rotateRight2(head, k)));
    }

    // 快慢指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode tmp = head;
        int len = 0;
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }

        k %= len;
        if (k == 0) return head;

        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    // 闭合成环 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode tmp = head;
        int len = 1;
        while (tmp.next != null) {
            len++;
            tmp = tmp.next;
        }

        k %= len;
        if (k == 0) return head;

        // 闭合成环
        tmp.next = head;

        tmp = head;
        for (int i = 0; i < len - k - 1; i++) {
            tmp = tmp.next;
        }

        head = tmp.next;
        tmp.next = null;
        return head;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))));
    }

}
