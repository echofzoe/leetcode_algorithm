package algorithm.leetcode.dpAndGreedy.打家劫舍;

import java.util.Arrays;

/**
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 *
 * @author echofzoe
 * @since 2021.4.15
 */
public class Lc_198_打家劫舍 {

    public static void main(String[] args) {
        Lc_198_打家劫舍 lc = new Lc_198_打家劫舍();
        int[] input = {2, 7, 9, 3, 1};

        System.out.println("在房屋群" + Arrays.toString(input) + "中能偷窃到的最高金额是" + lc.robDp(input));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可优化为O(1)
    // - dp[i]表示偷窃到第i间房子时所能得到的最高偷窃金额
    // - 状态转移方程: dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
    public int robDp(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // dp 优化 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int robDpOptimization(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int first, second;
        // base case
        first = nums[0];
        second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int tmp = Math.max(first + nums[i], second);
            first = second;
            second = tmp;
        }

        return second;
    }

}
