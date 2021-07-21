package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机含手续费
 * <P>https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/</P>
 *
 * @author echofzoe
 * @updated 2021.7.21
 * @since unknown
 */
public class Lc_714_买卖股票的最佳时机含手续费 {

    public static void main(String[] args) {
        Lc_714_买卖股票的最佳时机含手续费 lc = new Lc_714_买卖股票的最佳时机含手续费();

        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        System.out.println("给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。\n" +
                "你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。\n" +
                "返回获得利润的最大值。\n" +
                "注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。\n");
        System.out.println("输入：prices = " + Arrays.toString(prices));
        System.out.println("输出：" + lc.maxProfitDp(prices, fee));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxProfitDp(int[] prices, int fee) {
        int n = prices.length;

        // dp[i][0] 表示第i天不持有股票状态下的最大利润, dp[i][1] 表示第i天持有股票状态下的最大利润
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
