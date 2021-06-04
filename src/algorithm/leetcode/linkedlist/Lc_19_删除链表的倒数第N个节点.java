package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 删除链表的倒数第 N 个节点
 * <P>https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.6.4
 */
public class Lc_19_删除链表的倒数第N个节点 {

    public static void main(String[] args) {
        Lc_19_删除链表的倒数第N个节点 lc = new Lc_19_删除链表的倒数第N个节点();

        ListNode head = new ListNode(1);
        lc.linkedListInitialize(head);  // [1,2,3,4,5]
        int n = 2;

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 删除了倒数第 " + n + " 个节点后的结果为 " + ListNodeSerialize.serialize(lc.removeNthFromEnd_Stack(head, n)));
    }

    // 一趟遍历 - 时间复杂度 O(L) L为链表长度 - 空间复杂度 O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1, head);

        ListNode a = dummy, b = dummy;
        for (int i = 1; i <= n + 1; i++) b = b.next;

        while (b != null) {
            a = a.next;
            b = b.next;
        }

        ListNode c = a.next;
        a.next = a.next.next;
        c.next = null;

        return dummy.next;
    }

    // 栈 - 时间复杂度 O(L) L为链表长度 - 空间复杂度 O(L)
    public ListNode removeNthFromEnd_Stack(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1, head);

        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.offerLast(cur);
            cur = cur.next;
        }

        for (int i = 1; i <= n; i++) {
            stack.pollLast();
        }

        ListNode pre = stack.peekLast();
        ListNode del = pre.next;
        pre.next = pre.next.next;
        del.next = null;

        return dummy.next;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }
}
