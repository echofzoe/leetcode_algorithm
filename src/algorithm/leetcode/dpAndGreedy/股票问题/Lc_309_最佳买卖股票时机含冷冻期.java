package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;

/**
 * 最佳买卖股票时机含冷冻期
 * <P>https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/</P>
 *
 * @author echofzoe
 * @since 2021.7.21
 */
public class Lc_309_最佳买卖股票时机含冷冻期 {

    public static void main(String[] args) {
        Lc_309_最佳买卖股票时机含冷冻期 lc = new Lc_309_最佳买卖股票时机含冷冻期();

        int[] prices = {1, 2, 3, 0, 2};
        
        System.out.println("给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。\u200B\n" +
                "设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:\n" +
                "  - 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。\n" +
                "  - 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。\n");
        System.out.println("输入：prices = " + Arrays.toString(prices));
        System.out.println("输出：" + lc.maxProfit(prices));  // 3
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // dp[i][0] 表示第i天不持有股票 且不处于冷冻期 的状态下的最大利润
        // dp[i][1] 表示第i天持有股票的状态下的最大利润
        // dp[i][2] 表示第i天不持有股票 且处于冷冻期 的状态下的最大利润
        int[][] dp = new int[n][3];
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            int cur = prices[i];
            // 今天不持股票 且不处于冷冻期 = max(昨天不持股票 且不处于冷冻期，昨天不持股票 且处于冷冻期)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            // 今天持有股票 = max(昨天持有股票，昨天不持有股票 且不处于冷冻期 - 今天股票价格)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - cur);
            // 今天不持股票 且处于冷冻期 = 昨天持有股票 + 今天的股票价格
            dp[i][2] = dp[i - 1][1] + cur;
        }

        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }

}
