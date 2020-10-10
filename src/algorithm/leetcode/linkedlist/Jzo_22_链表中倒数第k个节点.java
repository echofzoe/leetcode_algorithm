package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Jzo_22_链表中倒数第k个节点 {

    // 链表中倒数第k个节点
    // - 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
    // - 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
    // https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/

    public static void main(String[] args) {
        Jzo_22_链表中倒数第k个节点 lc = new Jzo_22_链表中倒数第k个节点();
        ListNode head = new ListNode(1);
        lc.linkedListInitialize(head);    // head = [1,2,3,4,5]
        int k = 2;

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 中从倒数第 " + k + " 个节点开始的链表为 " + ListNodeSerialize.serialize(lc.getKthFromEnd(head, k)));
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head, p2 = head;

        for (int i = 0; i < k; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }
}
