package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

/**
 * 删除排序链表中的重复元素
 * <P>https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/</P>
 *
 * @author echofzoe
 * @since unknown
 * @date 2021.7.1
 */
public class Lc_83_删除排序链表中的重复元素 {

    public static void main(String[] args) {
        Lc_83_删除排序链表中的重复元素 lc = new Lc_83_删除排序链表中的重复元素();
        ListNode head = new ListNode(1);
        lc.listInitialize(head);    // [1,1,2,3,3]

        System.out.println("存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。\n" +
                "返回同样按升序排列的结果链表。\n" +
                "注：\n" +
                "  - 链表中节点数目在范围 [0, 300] 内\n" +
                "  - -100 <= Node.val <= 100\n" +
                "  - 题目数据保证链表已经按升序排列\n");
        System.out.println("输入：head = " + ListNodeSerialize.serialize(head));
        System.out.println("输出：" + ListNodeSerialize.serialize(lc.deleteDuplicates(head)));  // [1,2,3]
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-101, head);
        ListNode tail = dummy;

        while (head != null) {
            if (tail.val != head.val) {
                tail.next = head;
                tail = tail.next;
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
