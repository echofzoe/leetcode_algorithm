package algorithm.leetcode.sort;

import algorithm.leetcode.utils.ListNode;
import algorithm.leetcode.utils.ListNodeSerialize;

import java.util.*;

public class Lc_23_合并K个升序链表 {

    // 合并K个升序链表
    // https://leetcode-cn.com/problems/merge-k-sorted-lists/

    public static void main(String[] args) {
        Lc_23_合并K个升序链表 lc = new Lc_23_合并K个升序链表();
        ListNode[] input = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6)),
                null
        };

        System.out.print("将升序链表数组[");
        for (int i = 0; i < input.length; i++) {
            System.out.print(ListNodeSerialize.serialize(input[i]));
            if (i != input.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]合并到一个升序链表中的结果是" + ListNodeSerialize.serialize(lc.mergeKListsHeap(input)));
    }

    // 小根堆 - 时间复杂度 O(N*logN) - 空间复杂度 O(N) 为优先队列中节点数
    public ListNode mergeKListsHeap(ListNode[] lists) {
        Queue<ListNode> pg = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            ListNode tmp = list;
            while (tmp != null) {
                ListNode tmp1 = tmp;
                tmp = tmp.next;

                tmp1.next = null;    // 断开原链接,避免结果中有环
                pg.offer(tmp1);
            }
        }

        ListNode dummy = new ListNode(0), res = dummy;
        while (!pg.isEmpty()) {
            dummy.next = pg.poll();
            dummy = dummy.next;
        }

        return res.next;
    }

    // K指针 - 时间复杂度 O(N*K) - 空间复杂度 O(1)
    public ListNode mergeKListsKPoint(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (true) {
            ListNode minPoint = null;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (minPoint == null || lists[i].val < minPoint.val) {
                    minPoint = lists[i];
                    minIndex = i;
                }
            }
            if (minIndex == -1) break;

            tail.next = minPoint;
            tail = tail.next;
            lists[minIndex] = lists[minIndex].next;
        }

        return dummy.next;
    }

}
