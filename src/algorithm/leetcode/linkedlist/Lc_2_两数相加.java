package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

/**
 * 两数相加
 * <P>https://leetcode-cn.com/problems/add-two-numbers/</P>
 *
 * @author echofzoe
 * @since 2021.5.19
 */
public class Lc_2_两数相加 {

    public static void main(String[] args) {
        Lc_2_两数相加 lc = new Lc_2_两数相加();

        ListNode l1 = new ListNode(), l2 = new ListNode();
        lc.listInitialize(l1, l2);

        System.out.println("给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。\n" +
                "请你将两个数相加，并以相同形式返回一个表示和的链表。\n" +
                "你可以假设除了数字 0 之外，这两个数都不会以 0 开头。");
        System.out.println("输入：l1 = " + ListNodeSerialize.serialize(l1) + ", l2 = " + ListNodeSerialize.serialize(l2));
        System.out.println("输出：" + ListNodeSerialize.serialize(lc.addTwoNumbers(l1, l2)));  // [7,0,4,0,1]
    }

    // 模拟 - 时间复杂度 O(max(M,N)) - 空间复杂度 O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, l1), tail = new ListNode(-1);

        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;

            sum += carry;
            sum += l1 == null ? 0 : l1.val;
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            sum %= 10;

            if (l1 == null) {
                tail.next = new ListNode(sum);
                l1 = tail.next;
            }
            else l1.val = sum;

            tail = l1;
            l1 = l1.next;
        }

        if (carry > 0) tail.next = new ListNode(carry);

        return dummy.next;
    }

    private void listInitialize(ListNode l1, ListNode l2) {
        // l1 = [2,4,9]
        ListNode t1 = l1;
        t1.val = 2;
        t1.next = new ListNode(4);
        t1 = t1.next;
        t1.next = new ListNode(9);

        // l2 = [5,6,4,9]
        ListNode t2 = l2;
        t2.val = 5;
        t2.next = new ListNode(6);
        t2 = t2.next;
        t2.next = new ListNode(4);
        t2 = t2.next;
        t2.next = new ListNode(9);
    }

}
