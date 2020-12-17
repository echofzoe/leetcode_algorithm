package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.Map;

public class Lc_714_买卖股票的最佳时机含手续费 {

    // 买卖股票的最佳时机含手续费
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

    public static void main(String[] args) {
        Lc_714_买卖股票的最佳时机含手续费 lc = new Lc_714_买卖股票的最佳时机含手续费();
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int[] prices2 = {2, 3, 1, 16, 7, 9};
        int fee = 2;

        System.out.println("在股票价格区间" + Arrays.toString(prices1) + "和交易手续费" + fee + "的条件下所能获取的最大利润为" + lc.maxProfitDp(prices1, fee));
        System.out.println("在股票价格区间" + Arrays.toString(prices2) + "和交易手续费" + fee + "的条件下所能获取的最大利润为" + lc.maxProfitGreedy(prices2, fee));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i][0] 表示第i天不持有股票状态下的最大利润
    // - dp[i][1] 表示第i天持有股票状态下的最大利润
    public int maxProfitDp(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            // 卖出（之前已买入） - 一笔完整的交易 - 需要手续费
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);

            // 买入 - 不需手续费
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    // dp + 空间优化 - 时间复杂度 O(N) - 空间复杂度 O(1)
    // - dp[i][0] 表示第i天不持有股票状态下的最大利润
    // - dp[i][1] 表示第i天持有股票状态下的最大利润
    public int maxProfitDpOptimization(int[] prices, int fee) {
        // base case
        int dp0 = 0, dp1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i] - fee);
            dp1 = Math.max(dp1, tmp - prices[i]);
        }

        return dp0;
    }
    
    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxProfitGreedy(int[] prices, int fee) {
        int n = prices.length;

        int buy = prices[0] + fee;

        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }

        return profit;
    }

}
