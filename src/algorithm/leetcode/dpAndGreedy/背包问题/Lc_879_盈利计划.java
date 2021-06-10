package algorithm.leetcode.dpAndGreedy.背包问题;

import java.util.Arrays;

/**
 * 盈利计划
 * <P>https://leetcode-cn.com/problems/profitable-schemes/</P>
 *
 * @author echofzoe
 * @since 2021.6.9
 */
public class Lc_879_盈利计划 {

    public static void main(String[] args) {
        Lc_879_盈利计划 lc = new Lc_879_盈利计划();

        int n = 5, minProfit = 3;
        int[] group = {2, 2}, profit = {2, 3};

        System.out.println("集团里有 n 名员工，他们可以完成各种各样的工作创造利润。\n" +
                "第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。\n" +
                "工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。\n" +
                "有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。\n");
        System.out.println("输入：n = " + n + ", minProfit = " + minProfit + ", group = " + Arrays.toString(group) + ", profit = " + Arrays.toString(profit));
        System.out.println("输出：" + lc.profitableSchemes(n, minProfit, group, profit));
    }

    // DP - 时间复杂度 O(l * n * minProfit) 其中l为数组group的长度 - 空间复杂度 O(l * n * minProfit)
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // 工作总数
        int l = group.length;

        int MOD = (int) (1e9 + 7);

        // dp[i][j][k] 表示考虑前i件物品，使用人数不超过j，所得利润至少为k的方案数
        /*
            边界条件：{
                1. 在0份工作里选择j个员工从而获得的工作利润至少为0的盈利计划的总数是1
                - dp[0][j][0] = 1, 0 <= j <= n
            }
            状态转移方程：{
                1. 当前选择的员工人数小于当前工作需要的员工人数以至于无法完成工作
                - dp[i][j][k] = dp[i - 1][j][k], j < curGroup
                2. 当前选择的员工人数大于等于当前工作需要的员工人数，故可以完成工作
                - dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - curGroup][max(0, k - curProfit)], j >= curGroup
            }
         */
        int[][][] dp = new int[l + 1][n + 1][minProfit + 1];
        // base case
        for (int j = 0; j <= n; j++) dp[0][j][0] = 1;

        for (int i = 1; i <= l; i++) {
            int curGroup = group[i - 1], curProfit = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < curGroup) dp[i][j][k] = dp[i - 1][j][k];
                    else dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - curGroup][Math.max(0, k - curProfit)]) % MOD;
                }
            }
        }

        return dp[l][n][minProfit];
    }

    // DP 空间优化 - 时间复杂度 O(l * n * minProfit) 其中l为数组group的长度 - 空间复杂度 O(n * minProfit)
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        // 工作总数
        int l = group.length;

        int MOD = (int) (1e9 + 7);

        int[][] dp = new int[n + 1][minProfit + 1];
        // base case
        for (int j = 0; j <= n; j++) dp[j][0] = 1;

        for (int i = 1; i <= l; i++) {
            int curGroup = group[i - 1], curProfit = profit[i - 1];
            for (int j = n; j >= curGroup; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - curGroup][Math.max(0, k - curProfit)]) % MOD;
                }
            }
        }

        return dp[n][minProfit];
    }

}
