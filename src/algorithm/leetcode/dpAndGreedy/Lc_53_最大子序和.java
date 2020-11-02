package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_53_最大子序和 {

    // 最大子序和
    // https://leetcode-cn.com/problems/maximum-subarray/

    public static void main(String[] args) {
        Lc_53_最大子序和 lc = new Lc_53_最大子序和();
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] input = {-2, 1};

        System.out.println("数组" + Arrays.toString(input) + "中的最大子序列和为 " + lc.maxSubArrayDP(input));
    }

    // dp - 时间复杂度 O(N) - 空间复杂度 O(N) 为dp数组的开销,也可以直接原地修改数组,省下这笔开销从而达到 O(1) 的空间复杂度
    // - dp数组中的每个值dp[i]代表着以nums[i]为结尾的最大子序和
    // - 状态转移方程 - f(i) = max(f(i - 1) + nums[i], nums[i])
    public int maxSubArrayDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int res = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }

        for (int j : dp) {
            res = Math.max(res, j);
        }

        return res;
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxSubArrayGreedy(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int cur = nums[0], res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            res = Math.max(cur, res);
        }

        return res;
    }

}
