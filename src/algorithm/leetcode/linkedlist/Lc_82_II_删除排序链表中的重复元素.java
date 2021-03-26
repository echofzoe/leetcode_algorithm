package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_82_II_删除排序链表中的重复元素 {

    // 删除排序链表中的重复元素 II
    // https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/

    public static void main(String[] args) {
        Lc_82_II_删除排序链表中的重复元素 lc = new Lc_82_II_删除排序链表中的重复元素();
        ListNode head = new ListNode(1);
        lc.listInitialize(head);    // [1,1,2,3,3]

        System.out.println("去除" + ListNodeSerialize.serialize(head) + "中所有重复的元素，只保留原始链表中没有重复出现的数字的结果是" + ListNodeSerialize.serialize(lc.deleteDuplicates(head)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(-1, head);
        ListNode tail = dummy;

        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = head;
            }

            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }

            head = head.next;
        }

        tail.next = null;
        return dummy.next;
    }

    private void listInitialize(ListNode head) {
        head.val = 1;
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
    }

}
