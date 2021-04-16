package algorithm.leetcode.dpAndGreedy.打家劫舍;

import java.util.Arrays;

/**
 * 打家劫舍 II
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 *
 * @author echofzoe
 * @date 2021.3.26
 */
public class Lc_213_打家劫舍_II {

    public static void main(String[] args) {
        Lc_213_打家劫舍_II lc = new Lc_213_打家劫舍_II();
        int[] nums = {1, 2, 3, 1};

        System.out.println("按照题意实施盗窃，能够偷窃到的最高金额是" + lc.rob(nums));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i]表示偷窃到第i间房子时所能得到的最高偷窃金额
    // - 状态转移方程: {
    //   dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
    // }
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        // 房间数量 >2 时，才能进行有效拆分计算
        return Math.max(helper(nums, 0, n - 1), helper(nums, 1, n));
    }

    private int helper(int[] nums, int start, int end) {
        int[] dp = new int[end];
        // base case
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[end - 1];
    }

}
