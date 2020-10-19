package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;
import org.junit.Test;

import java.util.Stack;

public class Lc_19_删除链表的倒数第N个节点 {

    // 删除链表的倒数第N个节点
    // https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

    public static void main(String[] args) {
        Lc_19_删除链表的倒数第N个节点 lc = new Lc_19_删除链表的倒数第N个节点();
        ListNode head = new ListNode(1);
        lc.linkedListInitialize(head);    // head = [1,2,3,4,5]
        int n = 2;

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 删除了倒数第 " + n + " 个节点后的结果为 " + ListNodeSerialize.serialize(lc.removeNthFromEnd_Stack(head, n)));
    }

    // 一趟遍历 - 时间复杂度 O(L) L为链表长度 - 空间复杂度 O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(0, head);
        ListNode p1 = dummy, p2 = head;

        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;
        return dummy.next;
    }

    // 栈 - 时间复杂度 O(L) L为链表长度 - 空间复杂度 O(L)
    public ListNode removeNthFromEnd_Stack(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        Stack<ListNode> stack = new Stack<>();

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode prev = stack.peek();
        prev.next = prev.next.next;

        return dummy.next;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }
}
