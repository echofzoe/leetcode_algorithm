package algorithm.leetcode.dpAndGreedy.背包问题;

import java.util.Arrays;

/**
 * 分割等和子集
 * <P>https://leetcode-cn.com/problems/partition-equal-subset-sum/</P>
 *
 * @author echofzoe
 * @since 2021.6.7
 */
public class Lc_416_分割等和子集 {

    public static void main(String[] args) {
        Lc_416_分割等和子集 lc = new Lc_416_分割等和子集();

        int[] nums = {1, 5, 11, 5};
        
        System.out.println("给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.canPartition1(nums));
    }

    // DP - 时间复杂度 O(n*m) m为整个数组的元素和的一半 - 空间复杂度 O(n*m)
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int x : nums) sum += x;
        if ((sum & 1) == 1) return false;

        // dp[i][j] 表示前i个元素中是否有子集的元素和为j
        /*
            边界条件：{
                dp[0][0] = true;
            }
            状态转移方程：{
                dp[i][j] = dp[i - 1][j], j <  nums[i]
                dp[i][j] = dp[i - 1][j], j >= nums[i]
            }
         */
        int m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        // base case
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int cur = nums[i - 1];
            for (int j = 0; j <= m; j++) {
                if (j < cur) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] || dp[i - 1][j - cur];
            }
        }

        return dp[n][m];
    }

    // DP + 滚动数组 - 时间复杂度 O(n*m) m为整个数组的元素和的一半 - 空间复杂度 O(m)
    public boolean canPartition1(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for (int x : nums) sum += x;
        if ((sum & 1) == 1) return false;

        // dp[i] 表示数组nums中是否有子集的元素和为i
        int m = sum / 2;
        boolean[] dp = new boolean[m + 1];
        // base case
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            int cur = nums[i - 1];
            for (int j = m; j >= cur; j--) {
                dp[j] |= dp[j - nums[i - 1]];
            }
        }

        return dp[m];
    }

}
