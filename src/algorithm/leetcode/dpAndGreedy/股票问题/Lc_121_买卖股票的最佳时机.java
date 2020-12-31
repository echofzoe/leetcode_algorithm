package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_121_买卖股票的最佳时机 {

    // 买卖股票的最佳时机
    // - 最多只能进行一笔交易
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

    public static void main(String[] args) {
        Lc_121_买卖股票的最佳时机 lc = new Lc_121_买卖股票的最佳时机();
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        System.out.println("在股票价格数组" + Arrays.toString(prices) + "下，所能获取的最大利润是" + lc.maxProfitDp(prices));
        System.out.println("在股票价格数组" + Arrays.toString(prices2) + "下，所能获取的最大利润是" + lc.maxProfitMonotonyStack(prices2));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可优化到O(1)
    public int maxProfitDp(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        int[][] dp = new int[n][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }

    // 单调栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxProfitMonotonyStack(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        Deque<Integer> stack = new LinkedList<>();

        int profit = 0;
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peekFirst() >= prices[i]) stack.pollFirst();

            stack.offerFirst(prices[i]);
            if (stack.size() > 1) profit = Math.max(profit, stack.peekFirst() - stack.peekLast());
        }

        return profit;
    }

}
