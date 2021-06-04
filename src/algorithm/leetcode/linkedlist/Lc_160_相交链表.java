package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

/**
 * 相交链表
 * <P>https://leetcode-cn.com/problems/intersection-of-two-linked-lists/</P>
 *
 * @author echofzoe
 * @since 2021.6.4
 */
public class Lc_160_相交链表 {

    public static void main(String[] args) {
        Lc_160_相交链表 lc = new Lc_160_相交链表();

        ListNode intersect = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode A, B;
        A = new ListNode(4, new ListNode(1, intersect));
        B = new ListNode(5, new ListNode(0, new ListNode(1, intersect)));

        ListNode C = lc.getIntersectionNode(A, B);
        System.out.println("链表 " + ListNodeSerialize.serialize(A) + " 与链表 " + ListNodeSerialize.serialize(B)
                + (C == null ? " 不相交" : " 相交于 " + C.val));
    }

    // 双指针 - 时间复杂度 O(m + n) 其中m和n是分别是链表headA和headB的长度。两个指针同时遍历两个链表，每个指针遍历两个链表各一次 - 空间复杂度 O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA, b = headB;

        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

}
