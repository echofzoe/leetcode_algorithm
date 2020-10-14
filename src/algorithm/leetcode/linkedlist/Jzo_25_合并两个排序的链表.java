package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Jzo_25_合并两个排序的链表 {

    // 合并两个排序的链表
    // https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/

    public static void main(String[] args) {
        Jzo_25_合并两个排序的链表 lc = new Jzo_25_合并两个排序的链表();
        ListNode l1 = new ListNode(1) {{
            next = new ListNode(2);
            next.next = new ListNode(4);
        }};
        ListNode l2 = new ListNode(1) {{
            next = new ListNode(3);
            next.next = new ListNode(4);
        }};

        System.out.println("链表 " + ListNodeSerialize.serialize(l1) + " 和 " + ListNodeSerialize.serialize(l2) + " 合并后的新链表为 " + ListNodeSerialize.serialize(lc.mergeTwoLists(l1, l2)));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0), curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;

        return dummy.next;
    }
}
