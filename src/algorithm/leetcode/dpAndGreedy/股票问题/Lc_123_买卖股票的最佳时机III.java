package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;

public class Lc_123_买卖股票的最佳时机III {

    // 买卖股票的最佳时机 III
    // - 最多只能完成2笔交易
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/

    public static void main(String[] args) {
        Lc_123_买卖股票的最佳时机III lc = new Lc_123_买卖股票的最佳时机III();
        int[] prices = {3,3,5,0,0,3,1,4};
        
        System.out.println("在股票价格数组" + Arrays.toString(prices) + "内所能获取的最大利润为" + lc.maxProfit(prices));
    }

    // DP - 时间复杂度 O(N*min(N,k)) - 空间复杂度 O(N*min(N,k))
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int k = 2;
        int[][][] dp = new int[n][2][k + 1];
        // base case
        for (int i = 0; i <= k; i++) dp[0][1][i] = -prices[0];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }

        return Arrays.stream(dp[n - 1][0]).max().getAsInt();
    }

}
