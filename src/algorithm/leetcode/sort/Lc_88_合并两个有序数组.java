package algorithm.leetcode.sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_88_合并两个有序数组 {

    // 合并两个有序数组
    // https://leetcode-cn.com/problems/merge-sorted-array/

    public static void main(String[] args) {
        Lc_88_合并两个有序数组 lc = new Lc_88_合并两个有序数组();
        int[] nums1 = {1, 2, 4, 0, 0, 0};
        int[] nums2 = {3, 5, 6};
        int m = 3, n = 3;

        System.out.println("将数组" + Arrays.toString(nums2) + "合并入" + Arrays.toString(Arrays.copyOf(nums1, m)) + "并使得后者有序的结果是");
        lc.mergeHeap(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    // 堆排序 - 时间复杂度 O(N*logN) - 空间复杂度 O(N) N为优先队列中节点数
    public void mergeHeap(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> a - b) {{
            for (int i = 0; i < m; i++) {
                offer(nums1[i]);
            }
            for (int i = 0; i < n; i++) {
                offer(nums2[i]);
            }
        }};

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = pq.poll();
        }

    }

    // 双指针 从前往后 - 时间复杂度 O(n+m) - 空间复杂度 O(m)
    public void mergeDoublePoint1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = new int[m];
        System.arraycopy(nums1, 0, nums1Copy, 0, m);
        int p1 = 0;    // 指向nums1Copy
        int p2 = 0;    // 指向nums2
        int p = 0;    // 指向nums1

        while (p1 < m && p2 < n) {
            nums1[p++] = nums1Copy[p1] < nums2[p2] ? nums1Copy[p1++] : nums2[p2++];
        }

        if (p1 < m) {
            System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    // 双指针 从后往前 - 时间复杂度 O(n+m) - 空间复杂度 O(1)
    public void mergeDoublePoint2(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = m - 1;    // 指向nums1
        int idx2 = n - 1;    // 指向nums2
        int idx = m + n - 1;    // 指向nums1

        while (idx1 >= 0 && idx2 >= 0) {
            nums1[idx--] = nums1[idx1] < nums2[idx2] ? nums2[idx2--] : nums1[idx1--];
        }

//        while (idx2 >= 0) {
//            nums1[idx--] = nums2[idx2--];
//        }
        System.arraycopy(nums2, 0, nums1, 0, idx2 + 1);
    }

}
