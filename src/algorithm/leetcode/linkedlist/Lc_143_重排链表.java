package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.util.ArrayList;
import java.util.List;

public class Lc_143_重排链表 {

    // 重排链表
    // https://leetcode-cn.com/problems/reorder-list/

    public static void main(String[] args) {
        Lc_143_重排链表 lc = new Lc_143_重排链表();
        ListNode input1 = new ListNode(1);    // [1,2,3,4]
        ListNode input2 = new ListNode(1);    // [1,2,3,4,5]
        lc.linkedListInitialize(input1, input2);

        System.out.print("链表 " + ListNodeSerialize.serialize(input1) + " 按题目要求重排后的结果为 ");
        lc.reorderList_DoublePoint(input1);
        System.out.println(ListNodeSerialize.serialize(input1));

        System.out.print("链表 " + ListNodeSerialize.serialize(input2) + " 按题目要求重排后的结果为 ");
        lc.reorderList_LinearTable(input2);
        System.out.println(ListNodeSerialize.serialize(input2));
    }

    // 线性表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public void reorderList_LinearTable(ListNode head) {
        if (head == null) return;

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i++).next = list.get(j);

            if (i == j) break;

            list.get(j--).next = list.get(i);
        }

        list.get(i).next = null;    // 原链表中点即新链表最后节点
    }

    // 快慢指针找中点 + 原地修改链表 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public void reorderList_DoublePoint(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;

        // 找中点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast= fast.next.next;
        }

        // 按中点分割链表，将右半部分链表逆序
        ListNode head2 = slow.next;
        slow.next = null;
        head2 = reverseList(head2);

        // 交叉连接两个链表
        while (head2 != null) {
            ListNode temp = head2.next;
            head2.next = head.next;
            head.next = head2;

            head = head2.next;
            head2 = temp;
        }
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

    private void linkedListInitialize(ListNode input1, ListNode input2) {
        input1.next = new ListNode(2);
        input1.next.next = new ListNode(3);
        input1.next.next.next = new ListNode(4);
        
        input2.next = new ListNode(2);
        input2.next.next = new ListNode(3);
        input2.next.next.next = new ListNode(4);
        input2.next.next.next.next = new ListNode(5);
    }
}
