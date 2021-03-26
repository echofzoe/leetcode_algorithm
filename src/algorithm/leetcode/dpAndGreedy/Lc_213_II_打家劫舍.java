package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

/**
 * 打家劫舍 II
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 *
 * @author echofzoe
 * @date 2021.3.26
 */
public class Lc_213_II_打家劫舍 {

    public static void main(String[] args) {
        Lc_213_II_打家劫舍 lc = new Lc_213_II_打家劫舍();
        int[] nums = {1, 2, 3, 1};

        System.out.println("按照题意实施盗窃，能够偷窃到的最高金额是" + lc.rob(nums));
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // n > 1时，才可以对数组进行首位拆分计算的操作
        if (n == 1) return nums[0];

        return Math.max(helper(Arrays.copyOfRange(nums, 1, n)), helper(Arrays.copyOfRange(nums, 0, n - 1)));
    }

    private int helper(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.max(dp[i - 2 + 1] + nums[i], dp[i - 1 + 1]);
        }

        return dp[n];
    }

}
