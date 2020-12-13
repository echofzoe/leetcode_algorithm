package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_376_摆动序列 {

    // 摆动序列
    // https://leetcode-cn.com/problems/wiggle-subsequence/

    public static void main(String[] args) {
        Lc_376_摆动序列 lc = new Lc_376_摆动序列();
        int[] input1 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int[] input2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(Arrays.toString(input1) + "中作为摆动序列的最长子序列的长度是" + lc.wiggleMaxLengthGreedy(input1));
        System.out.println(Arrays.toString(input2) + "中作为摆动序列的最长子序列的长度是" + lc.wiggleMaxLengthDp(input2));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int wiggleMaxLengthGreedy(int[] nums) {
        int n = nums.length;

        if (n < 2) return n;

        int preTrend = nums[1] - nums[0];
        int res = preTrend == 0 ? 1 : 2;

        for (int i = 2; i < n; i++) {
            int trend = nums[i] - nums[i - 1];
            if ((trend > 0 && preTrend <= 0) || (trend < 0 && preTrend >= 0)) {
                res++;
                preTrend = trend;
            }
        }

        return res;
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
    public int wiggleMaxLengthDp(int[] nums) {
        int n = nums.length;

        if (n < 2) return n;

        int[] up = new int[n];
        int[] down = new int[n];

        // base case
        up[0] = down[0] = 1;

        for (int i = 1; i < n; i++) {
            // up trend
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[n - 1], down[n - 1]);
    }

}
