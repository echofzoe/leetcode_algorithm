package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_234_回文链表 {

    // 回文链表
    // https://leetcode-cn.com/problems/palindrome-linked-list/

    public static void main(String[] args) {
        Lc_234_回文链表 lc = new Lc_234_回文链表();
        // input - [1,2,2,1]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));

        System.out.println("链表 " + ListNodeSerialize.serialize(head) + (lc.isPalindrome_DoublePoint(head) ? " 是" : " 不是") + "回文链表.");
    }

    // 递归 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isPalindrome_Recursive(ListNode head) {
        prev = head;
        return recursivelyCheck(head);
    }

    ListNode prev;

    private boolean recursivelyCheck(ListNode curr) {
        if (curr != null) {
            if (!recursivelyCheck(curr.next)) {
                return false;
            }
            if (curr.val != prev.val) {
                return false;
            }
            prev = prev.next;
        }
        return true;
    }

    // 快慢指针找中点 + 原地修改链表判定回文 + 还原链表 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean isPalindrome_DoublePoint(ListNode head) {
        if (head == null) return true;

        boolean res = true;

        // 快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        ListNode p1 = head, p2 = reverseList(head2);
        ListNode temp = p2;
        while (p2 != null) {
            if (p1.val != p2.val) {
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverseList(temp);

        return res;
    }

    private ListNode reverseList(ListNode head) {
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
}
