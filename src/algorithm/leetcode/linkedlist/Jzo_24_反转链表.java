package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.util.Stack;

public class Jzo_24_反转链表 {

    // 反转链表
    // https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/

    public static void main(String[] args) {
        Jzo_24_反转链表 lc = new Jzo_24_反转链表();
        ListNode head = new ListNode(1);
        lc.listInitialize(head);    // [1,2,3,4,5]

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 反转后的结果为 " + ListNodeSerialize.serialize(lc.reverseList_Stack(head)));
    }

    // 直接更改指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode tail = head;
        head = head.next;
        tail.next = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = tail;
            tail = head;
            head = temp;
        }

        return tail;
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N) 为递归栈的开销
    public ListNode reverseList_Recursive(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList_Recursive(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public ListNode reverseList_Stack(ListNode head) {
        if (head == null) return null;

        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (!stack.isEmpty()) {
            dummy.next = stack.pop();
            dummy.next.next = null;
            dummy = dummy.next;
        }

        return res.next;
    }

    private void listInitialize(ListNode head) {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }
}
