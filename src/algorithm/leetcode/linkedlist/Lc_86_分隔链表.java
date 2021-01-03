package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_86_分隔链表 {

    // 分隔链表
    // https://leetcode-cn.com/problems/partition-list/

    public static void main(String[] args) {
        Lc_86_分隔链表 lc = new Lc_86_分隔链表();
        ListNode head = new ListNode(0);
        lc.linkedListInitialize(head);    // 1->4->3->2->5->2
        int x = 3;

        System.out.println("将链表" + ListNodeSerialize.serialize(head) + "按特定值" + x + "分隔并保留两个分区中每个节点的初始相对位置的结果是" + ListNodeSerialize.serialize(lc.partition(head, x)));
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);

        ListNode smallHead = small;
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }

            head = head.next;
        }

        large.next = null;
        small.next = largeHead.next;

        return smallHead.next;
    }

    private void linkedListInitialize(ListNode head) {
        head.val = 1;
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
    }

}
