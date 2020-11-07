package algorithm.leetcode.mergeSort;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

public class Lc_148_排序链表 {

    // 排序链表
    // https://leetcode-cn.com/problems/sort-list/

    public static void main(String[] args) {
        Lc_148_排序链表 lc = new Lc_148_排序链表();
        ListNode head1 = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));    // [4,2,1,3]
        ListNode head2 = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));    // [-1,5,3,4,0]

        System.out.println("链表" + ListNodeSerialize.serialize(head1) + "按升序排列后的结果为" + ListNodeSerialize.serialize(lc.sortList(head1)));
        System.out.println("链表" + ListNodeSerialize.serialize(head2) + "按升序排列后的结果为" + ListNodeSerialize.serialize(lc.sortListOptimization(head2)));
    }

    // 归并排序 + 递归 - 时间复杂度 O(N*logN) 分割链表O(N) 递归函数调用O(logN) 归并排序O(N) - 空间复杂度 O(logN) 为递归开销
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 找中间节点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 递归的分割链表
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 排序
        ListNode dummy = new ListNode(0), res = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
            } else {
                dummy.next = right;
                right = right.next;
            }
            dummy = dummy.next;
        }
        dummy.next = left == null ? right : left;

        return res.next;
    }

    // 归并排序优化 不用递归分割链表 直接原地排序 - 时间复杂度 O(N*logN) - 空间复杂度 O(1)
    public ListNode sortListOptimization(ListNode head) {
        ListNode h, h1, h2, pre, res = new ListNode(0);
        res.next = head;

        // 计算长度
        h = head;
        int length = 0;
        while (h != null) {
            h = h.next;
            length++;
        }

        // 原地归并排序
        int intv = 1;    // 归并的子区间长度初始化
        while (intv < length) {
            pre = res;
            h = res.next;
            while (h != null) {
                // 找出两个归并候选区间
                int i = intv;
                h1 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }

                if (i > 0) break;    // 当前元素不够分割成两个子区间时,跳出循环

                i = intv;
                h2 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }

                // 排序
                int c1 = intv, c2 = intv - i;    // 两个子区间的长度
                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }
                pre.next = c1 == 0 ? h2 : h1;
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }
                pre.next = h;
            }
            intv *= 2;
        }
        return res.next;
    }

}
