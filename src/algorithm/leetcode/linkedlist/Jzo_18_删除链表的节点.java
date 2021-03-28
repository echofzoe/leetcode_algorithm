package algorithm.leetcode.linkedlist;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Jzo_18_删除链表的节点 {

    // 删除链表的节点
    // https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/

    public static void main(String[] args) {
        Jzo_18_删除链表的节点 lc = new Jzo_18_删除链表的节点();
        ListNode head = new ListNode(4);
        lc.linkedListInitialize(head);    // head = [4,5,1,9]
        int val = 5;
        
        System.out.println("链表 " + ListNodeSerialize.serialize(head) + " 删除了节点 " + val + " 后的结果为 " + ListNodeSerialize.serialize(lc.deleteNode(head, val)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;

        if (head.val == val) return head.next;

        ListNode foo = head;
        while (foo.next != null) {
            if (foo.next.val == val) {
                foo.next = foo.next.next;
                break;
            }
            foo = foo.next;
        }

        return head;
    }

    private void linkedListInitialize(ListNode head) {
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
    }
}
