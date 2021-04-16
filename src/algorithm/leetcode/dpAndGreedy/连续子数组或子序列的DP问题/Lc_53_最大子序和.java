package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

public class Lc_53_最大子序和 {

    // 最大子序和
    // https://leetcode-cn.com/problems/maximum-subarray/

    public static void main(String[] args) {
        Lc_53_最大子序和 lc = new Lc_53_最大子序和();
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("数组" + Arrays.toString(input) + "中的最大子序列和为 " + lc.maxSubArrayDP(input));
    }

    // dp - 时间复杂度 O(N) - 空间复杂度 O(N) 为dp数组的开销,也可以直接原地修改数组,省下这笔开销从而达到 O(1) 的空间复杂度
    // - dp[i] 表示以 nums[i] 为结尾的连续子数组的最大和
    // - 状态转移方程: {
    //   dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])
    // }
    public int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // dp[i] 表示以 nums[i] 结尾的子数组的最大和
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxSubArrayGreedy(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int cur = nums[0], res = nums[0];

        for (int i = 1; i < n; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            res = Math.max(cur, res);
        }

        return res;
    }

}
