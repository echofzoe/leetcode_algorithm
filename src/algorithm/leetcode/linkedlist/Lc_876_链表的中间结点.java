package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_876_链表的中间结点 {

    // 链表的中间结点
    // https://leetcode-cn.com/problems/middle-of-the-linked-list/

    public static void main(String[] args) {
        Lc_876_链表的中间结点 lc = new Lc_876_链表的中间结点();
        ListNode head = new ListNode(1);
        lc.listInitialize(head);    // [1,2,3,4,5,6]

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 从中间节点截断的序列化形式是 " + ListNodeSerialize.serialize(lc.middleNode(head)));
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private void listInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
    }
}
