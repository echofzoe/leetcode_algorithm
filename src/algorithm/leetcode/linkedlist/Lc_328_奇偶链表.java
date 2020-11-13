package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_328_奇偶链表 {

    // 奇偶链表
    // https://leetcode-cn.com/problems/odd-even-linked-list/

    public static void main(String[] args) {
        Lc_328_奇偶链表 lc = new Lc_328_奇偶链表();
        ListNode head = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(7)))))));    // 2->1->3->5->6->4->7->NULL

        System.out.println("将链表" + ListNodeSerialize.serialize(head) + "的所有奇数节点和偶数节点分别排在一起的结果是" + ListNodeSerialize.serialize(lc.oddEvenList(head)));
    }

    // in place - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;

        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;
    }

}
