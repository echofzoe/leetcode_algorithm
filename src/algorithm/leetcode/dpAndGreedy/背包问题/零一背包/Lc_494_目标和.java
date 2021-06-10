package algorithm.leetcode.dpAndGreedy.背包问题.零一背包;

import java.util.Arrays;

/**
 * 目标和
 * <P>https://leetcode-cn.com/problems/target-sum/</P>
 *
 * @author echofzoe
 * @since 2021.6.7
 */
public class Lc_494_目标和 {

    public static void main(String[] args) {
        Lc_494_目标和 lc = new Lc_494_目标和();

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println("给你一个整数数组 nums 和一个整数 target 。\n" +
                "向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：\n" +
                "  - 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 \"+2-1\" 。\n" +
                "返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。");
        System.out.println("注：1 <= nums.length <= 20\n" +
                "0 <= nums[i] <= 1000\n" +
                "0 <= sum(nums[i]) <= 1000\n" +
                "-1000 <= target <= 100\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findTargetSumWays(nums, target));  // 5
    }

    // DP - 时间复杂度 O(N*L) N为数组长度，L为数组元素和范围 - 空间复杂度 O(N*L)
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) sum += num;
        if (target > sum) return 0;

        // sum 有正负，这里扩展为 2 倍的 sum，方便用数组访问
        // dp[i][j] 表示在前 i 个元素中进行运算得到的元素和为 j 的方法数量
        // 注：dp数组的第二维中，[0, sum]表示的实际元素和区间是[-sum, 0]，[sum, 2 * sum]表示的实际元素和区间是[0, sum]
        int m = 2 * sum;
        int[][] dp = new int[n + 1][m + 1];
        // base case
        dp[0][sum] = 1;  // 前0个元素构成元素和为0的方案数是1

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= nums[i - 1]) dp[i][j] += dp[i - 1][j - nums[i - 1]];
                if (j + nums[i - 1] <= m) dp[i][j] += dp[i - 1][j + nums[i - 1]];
            }
        }

        return dp[n][target + sum];
    }

    // DP 官方解法 - 时间复杂度 O(n×(sum−target)) - 空间复杂度 O(n×(sum−target))
    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int diff = sum - target;
        if (diff < 0 || (diff & 1) == 1) return 0;

        int neg = diff / 2;

        // 记数组的元素和为sum，经过负号运算的元素之和为neg，则经过正号运算的元素之和为sum-neg
        // 得表达式 (sum - neg) - neg = target 即 sum - 2neg = target 即 neg = (sum - target) / 2

        // dp[i][j] 表示数组的[0, i - 1]下标范围内所有元素进行运算得到的元素和为j的方法数量
        /*
            边界条件：{
                dp[0][j] = 1, j =  0
                dp[0][j] = 0, j >= 1
            }
            状态转移方程：{
                dp[i][j] = dp[i - 1][j], j < nums[i]
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]], j >= nums[i]
            }
         */
        int[][] dp = new int[n + 1][neg + 1];
        // base case
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                if (j < num) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
            }
        }

        return dp[n][neg];
    }

}
