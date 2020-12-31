package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;

public class Lc_122_买卖股票的最佳时机II {

    // 买卖股票的最佳时机 II
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/

    public static void main(String[] args) {
        Lc_122_买卖股票的最佳时机II lc = new Lc_122_买卖股票的最佳时机II();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {7, 6, 1, 3, 1, 4, 8};


        System.out.println("在股票价格数组" + Arrays.toString(prices1) + "内所能获取的最大利润为" + lc.maxProfitGreedy(prices1));
        System.out.println("在股票价格数组" + Arrays.toString(prices2) + "内所能获取的最大利润为" + lc.maxProfitHill(prices2));
        System.out.println("在股票价格数组" + Arrays.toString(prices3) + "内所能获取的最大利润为" + lc.maxProfitDp(prices3));
        System.out.println("在股票价格数组" + Arrays.toString(prices3) + "内所能获取的最大利润为" + lc.maxProfitDpOptimization(prices3));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxProfitGreedy(int[] prices) {
        if (prices.length == 0) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }

        return profit;
    }

    // 山脉数组 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxProfitHill(int[] prices) {
        int low = 0, high, profit = 0;

        while (low + 1 < prices.length) {
            // 找山脚
            if (prices[low] >= prices[low + 1]) {
                low++;
            }
            // 找山顶
            else {
                high = low + 1;
                while (high + 1 < prices.length && prices[high] < prices[high + 1]) high++;
                profit += prices[high] - prices[low];
                low = high + 1;
            }
        }

        return profit;
    }

    // dp - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i][0] 表示第i天不持有股票状态下的最大利润
    // - dp[i][1] 表示第i天持有股票状态下的最大利润
    public int maxProfitDp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    // dp + 空间优化 - 时间复杂度 O(N) - 空间复杂度 O(1)
    // - dp[i][0] 表示第i天不持有股票状态下的最大利润
    // - dp[i][1] 表示第i天持有股票状态下的最大利润
    public int maxProfitDpOptimization(int[] prices) {
        // base case
        int dp0 = 0, dp1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, tmp - prices[i]);
        }

        return dp0;
    }

}
