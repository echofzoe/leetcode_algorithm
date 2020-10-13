package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_24_两两交换链表中的节点 {

    // 两两交换链表中的节点
    // https://leetcode-cn.com/problems/swap-nodes-in-pairs/

    public static void main(String[] args) {
        Lc_24_两两交换链表中的节点 lc = new Lc_24_两两交换链表中的节点();
        ListNode head = new ListNode(1);
        lc.linkedListInitialize(head);

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 两两交换节点后的结果为 " + ListNodeSerialize.serialize(lc.swapPairs_Recursion(head)));
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public ListNode swapPairs_Recursion(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode a = head.next;
        head.next = swapPairs_Recursion(a.next);
        a.next = head;

        return a;
    }

    // 迭代 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode swapPairs_Iteration(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;

            node1.next = node2.next;
            node2.next = node1;
            temp.next = node2;

            temp = node1;
        }

        return dummyHead.next;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }
}
