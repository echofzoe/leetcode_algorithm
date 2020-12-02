package algorithm.leetcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Lc_321_拼接最大数 {

    // 拼接最大数
    // https://leetcode-cn.com/problems/create-maximum-number/

    public static void main(String[] args) {
        Lc_321_拼接最大数 lc = new Lc_321_拼接最大数();
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;

        System.out.println("从数组" + Arrays.toString(nums1) + "和" + Arrays.toString(nums1) + "中按相对顺序取出" + k + "个数组成一个最大的新数为" + Arrays.toString(lc.maxNumber(nums1, nums2, k)));
    }

    // 单调栈
    // - 时间复杂度 O(k*(m+n+k^2))
    // -- 其中 m 和 n 分别是数组 nums1 和 nums2 的长度，k 是拼接最大数的长度
    // -- 两个子序列的长度之和为 k，最多有 k 种不同的长度组合。对于每一种长度组合，需要首先得到两个最大子序列，然后进行合并
    // -- 得到两个最大子序列的时间复杂度为线性，即 O(m+n)
    // -- 合并两个最大子序列，需要进行 k 次合并，每次合并需要进行比较，最坏情况下，比较的时间复杂度为 O(k)，因此合并操作的时间复杂度为 O(k^2)
    // -- 因此对于每一种长度组合，时间复杂度为 O(m+n+k^2)，总时间复杂度为 O(k*(m+n+k^2))
    // - 空间复杂度 O(k)
    // -- 其中 k 是拼接最大数的长度，每次从两个数组得到两个子序列，两个子序列的长度之和为 k
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int n = Math.max(n1, n2);

        int[] res = new int[k];
        int start = Math.max(0, k - n2), end = Math.min(k, n1);
        for (int i = start; i <= end; i++) {
            int[] tmp1 = maxSubquence(nums1, i);
            int[] tmp2 = maxSubquence(nums2, k - i);

            int[] curMaxSq = merge(tmp1, tmp2);
            if (compare(curMaxSq, 0, res, 0) > 0) {
                System.arraycopy(curMaxSq, 0, res, 0, k);
            }
        }

        return res;
    }

    private int[] maxSubquence(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> stack = new LinkedList<>();
        int p = n - k;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && p > 0 && nums[i] > stack.peekFirst()) {
                stack.pollFirst();
                p--;
            }

            stack.offerFirst(nums[i]);
        }

        while (p > 0) {
            stack.pollFirst();
            p--;
        }

        int[] res = new int[k];
        while (!stack.isEmpty()) {
            res[--k] = stack.pollFirst();
        }

        return res;
    }

    private int[] merge(int[] subSequence1, int[] subSequence2) {
        int n1 = subSequence1.length, n2 = subSequence2.length;
        if (n1 == 0) return subSequence2;
        if (n2 == 0) return subSequence1;

        int n = n1 + n2;
        int[] merged = new int[n];
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < n; i++) {
            if (compare(subSequence1, idx1, subSequence2, idx2) > 0) {
                merged[i] = subSequence1[idx1++];
            } else {
                merged[i] = subSequence2[idx2++];
            }
        }

        return merged;
    }

    private int compare(int[] subSequence1, int idx1, int[] subSequence2, int idx2) {
        int n1 = subSequence1.length, n2 = subSequence2.length;

        while (idx1 < n1 && idx2 < n2) {
            int diff = subSequence1[idx1] - subSequence2[idx2];
            if (diff != 0) {
                return diff;
            }
            idx1++;
            idx2++;
        }

        return (n1 - idx1) - (n2 - idx2);
    }

}
