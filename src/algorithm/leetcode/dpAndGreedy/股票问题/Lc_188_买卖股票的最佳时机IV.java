package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;

public class Lc_188_买卖股票的最佳时机IV {

    // 买卖股票的最佳时机 IV
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/

    public static void main(String[] args) {
        Lc_188_买卖股票的最佳时机IV lc = new Lc_188_买卖股票的最佳时机IV();
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        int[] prices2 = {2, 4, 1};

        System.out.println("在股票价格" + Arrays.toString(prices) + "中，最多完成" + k + "笔交易所能取得的最大利润是" + lc.maxProfitDp(k, prices));
        System.out.println("在股票价格数组" + Arrays.toString(prices2) + "中，最多完成" + k + "笔交易所能取得的最大利润是" + lc.maxProfitDp2(k, prices2));
    }

    // DP - 时间复杂度 O(N*min(N,k)) - 空间复杂度 O(N*min(N,k))
    public int maxProfitDp(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        k = Math.min(k, n / 2);

        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        // base case
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            // 不能设置成 Integer.MIN_VALUE，否则进行"-"操作时会溢出
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);

            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    // DP - 时间复杂度 O(N*min(N,k)) - 空间复杂度 O(N*min(N,k))
    public int maxProfitDp2(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        k = Math.min(k, n / 2);

        int[][][] dp = new int[n][2][k + 1];
        // base case
        // 不持股 - 默认为0
        // 持股
        for (int i = 0; i <= k; i++) dp[0][1][i] = -prices[0];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 不持股
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                // 持股
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }

        return Arrays.stream(dp[n - 1][0]).max().getAsInt();
    }

}
