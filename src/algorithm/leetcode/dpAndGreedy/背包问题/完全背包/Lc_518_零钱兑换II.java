package algorithm.leetcode.dpAndGreedy.背包问题.完全背包;

import java.util.Arrays;

/**
 * Lc_518_零钱兑换II
 * <P>https://leetcode-cn.com/problems/coin-change-2/</P>
 *
 * @author echofzoe
 * @since 2021.6.10
 */
public class Lc_518_零钱兑换II {

    public static void main(String[] args) {
        Lc_518_零钱兑换II lc = new Lc_518_零钱兑换II();

        int amount = 5;
        int[] coins = {1, 2, 5};

        System.out.println("给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。");
        System.out.println("输入：amount = " + amount + ", coins = " + Arrays.toString(coins));
        System.out.println("输出：" + lc.change1(amount, coins));  // 4
    }

    // DP - 时间复杂度 O(n * amount) - 空间复杂度 O(n * amount)
    public int change(int amount, int[] coins) {
        int n = coins.length;

        // dp[i][j] 表前i个硬币可以凑成的总金额为j的硬币组合数
        /*
            边界条件：{
                1. 前i个硬币可以凑成的总金额为0的硬币组合数为1，即选择0个硬币
                - dp[i][0] = 1
            }
            状态转移方程：{
                dp[i][j] = dp[i][j] + dp[i - 1][j - k * curCoin], 1<=i<=n && 0<=j<=amount && 0<=k*curCoin<=j
            }
         */
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i < n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            int curCoin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * curCoin <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * curCoin];
                }
            }
        }

        return dp[n][amount];
    }

    // DP 空间优化 - 时间复杂度 O(n * amount) - 空间复杂度 O(amount)
    public int change1(int amount, int[] coins) {
        int n = coins.length;

        int[] dp = new int[amount + 1];
        // base case
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int curCoin = coins[i - 1];
            for (int j = amount; j >= 0; j--) {
                for (int k = 1; k * curCoin <= j; k++) {
                    dp[j] += dp[j - k * curCoin];
                }
            }
        }

        return dp[amount];
    }

}
