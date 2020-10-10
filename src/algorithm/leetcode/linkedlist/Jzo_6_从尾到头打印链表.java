package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.util.Arrays;
import java.util.Stack;

public class Jzo_6_从尾到头打印链表 {

    // 从尾到头打印链表
    // https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/

    public static void main(String[] args) {
        Jzo_6_从尾到头打印链表 lc = new Jzo_6_从尾到头打印链表();
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 倒置后为 " + Arrays.toString(lc.reversePrint_Traverse(head)));
    }

    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] reversePrint_Stack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        ListNode foo = head;

        while (foo != null) {
            stack.push(foo);
            foo = foo.next;
        }

        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }

        return print;
    }

    // 两次遍历链表 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] reversePrint_Traverse(ListNode head) {
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        int[] print = new int[size];
        curr = head;
        while (curr != null) {
            print[(size--) - 1] = curr.val;
            curr = curr.next;
        }

        return print;
    }
}
