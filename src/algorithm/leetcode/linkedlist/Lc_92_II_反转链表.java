package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.awt.*;

public class Lc_92_II_反转链表 {

    public static void main(String[] args) {
        Lc_92_II_反转链表 lc = new Lc_92_II_反转链表();
        ListNode head = new ListNode(0);
        lc.listInitialize(head);    // [1,2,3,4,5]
        int left = 2, right = 4;

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 反转区间[" + left + ", " + right + "]后的结果为 " + ListNodeSerialize.serialize(lc.reverseBetween(head, left, right)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // precursor - 区间头结点前驱
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode leftNode = pre.next;

        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // successor - 区间尾结点后继
        ListNode succ = rightNode.next;

        // 切断区间
        pre.next = null;
        rightNode.next = null;

        // 反转链表区间
        reverseList(leftNode);

        // 接回原链表
        pre.next = rightNode;
        leftNode.next = succ;

        return dummy.next;
    }

    private void reverseList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode tail = head;
        head = head.next;
        tail.next = null;

        while (head != null) {
            ListNode tmp = head.next;
            head.next = tail;
            tail = head;
            head = tmp;
        }
    }

    private void listInitialize(ListNode head) {
        head.val = 1;
        for (int i = 1; i < 5; i++) {
            head.next = new ListNode(i + 1);
            head = head.next;
        }
    }

}
