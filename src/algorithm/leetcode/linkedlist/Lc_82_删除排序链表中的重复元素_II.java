package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

/**
 * 删除排序链表中的重复元素 II
 * <P>https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/</P>
 *
 * @author echofzoe
 * @since unknown
 * @date 2021.7.2
 */
public class Lc_82_删除排序链表中的重复元素_II {

    public static void main(String[] args) {
        Lc_82_删除排序链表中的重复元素_II lc = new Lc_82_删除排序链表中的重复元素_II();

        ListNode head = new ListNode(1);
        lc.listInitialize(head);  // [1,1,2,3,3]

        System.out.println("输入：head = " + ListNodeSerialize.serialize(head));
        System.out.println("输出：" + ListNodeSerialize.serialize(lc.deleteDuplicates(head)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(-1, head);
        ListNode tail = dummy;

        while (head != null) {
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = tail.next;
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
