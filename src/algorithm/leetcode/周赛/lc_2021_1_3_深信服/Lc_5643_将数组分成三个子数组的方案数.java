package algorithm.leetcode.周赛.lc_2021_1_3_深信服;

import java.util.Arrays;
import java.util.logging.Level;

public class Lc_5643_将数组分成三个子数组的方案数 {

    // 将数组分成三个子数组的方案数
    // https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays/

    public static void main(String[] args) {
        Lc_5643_将数组分成三个子数组的方案数 lc = new Lc_5643_将数组分成三个子数组的方案数();
        int[] nums = {1, 2, 2, 2, 5, 0};

        System.out.println("按题意分割数组" + Arrays.toString(nums) + "，有" + lc.waysToSplit(nums) + "种分割方案");
    }

    // 前缀和 + 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int waysToSplit(int[] nums) {
        int n = nums.length;

        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) preSum[i] = preSum[i - 1] + nums[i];

        final int mod = 1000000007;

        int res = 0;

        // 将数组 两刀 切成 三段
        // 第一刀后sum(left)的最大值
        int leftMax = preSum[n - 1] / 3;
        for (int i = 0; i < n - 2 && preSum[i] <= leftMax; i++) {
            // 二分查找第二刀后sum(mid)的最小值: sum(mid) == sum(left)
            int left = lowerBound(i + 1, n - 2, preSum, preSum[i] * 2);
            // 二分查找第二刀后sum(mid)的最大值: sum(mid) == (sum(mid) + sum(right)) / 2
            int right = upperBound(i + 2, n - 1, preSum, preSum[i] + (preSum[n - 1] - preSum[i]) / 2);

            if (left <= right) res = (res + (right - left + 1)) % mod;
        }

        return res;
    }

    private int lowerBound(int start, int end, int[] preSum, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (preSum[mid] < target) start = mid + 1;
            else end = mid;
        }

        return start;
    }

    private int upperBound(int start, int end, int[] preSum, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (preSum[mid] <= target) start = mid + 1;
            else end = mid;
        }

        return start - 1;
    }

}
