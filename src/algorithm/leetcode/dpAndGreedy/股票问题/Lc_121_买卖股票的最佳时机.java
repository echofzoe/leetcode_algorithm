package algorithm.leetcode.dpAndGreedy.股票问题;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 买卖股票的最佳时机
 * <P>- 最多只能进行一笔交易</P>
 * <P>https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/</P>
 * 
 * @author echofzoe 
 * @since unknown
 * @updated 2021.7.12
 */
public class Lc_121_买卖股票的最佳时机 {

    public static void main(String[] args) {
        Lc_121_买卖股票的最佳时机 lc = new Lc_121_买卖股票的最佳时机();
        
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices1 = {7, 6, 4, 3, 1};

        System.out.println("给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。\n" +
                "你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。\n" +
                "返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。\n" +
                "注：\n" +
                "  - 1 <= prices.length <= 105\n" +
                "  - 0 <= prices[i] <= 104\n");
        System.out.println("输入：prices = " + Arrays.toString(prices));
        System.out.println("输出：" + lc.maxProfit(prices));
        System.out.println("输入：prices = " + Arrays.toString(prices1));
        System.out.println("输出：" + lc.maxProfit1(prices1));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可优化到O(1)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        // dp[i][0] 表示在第i天未持股，dp[i][1] 表示在第i天持股
        /*
            初始状态：{
                1.第一天不持股，利润为零
                - dp[0][0] = 0
                2.第一天持股，利润为0-prices[0]，即第一天就花本金买入了股票
                - dp[0][1] = -prices[0]
            }
            状态转移方程：{
                1.第i天不持股，状态可由 第i-1天不持股 或者 第i-1天持股并于第i天出售股票 转移而来
                - dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
                2.第i天持股，状态可由 第i-1天不持股并于第i天买入股票 或者 第i-1天持股 转移而来
                - dp[i][1] = Math.max(0 - prices[i], dp[i - 1][1])
            }
         */
        int[][] dp = new int[n][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            int cur = prices[i];

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + cur);
            /*
                需要注意的是：”第i-1天不持股并于第i天买入股票“ 这个状态 总是为 -prices[i]，
                因为股票只能买入一次，买入时直接扣除本金，利润由0变为负值；
                但是 ”第i-1天持股并于第i天出售股票“ 这个状态不是固定的，
                因为状态 ”第i-1天不持股并于第i天买入股票“ 已经保证了股票只买入一次。
             */
            dp[i][1] = Math.max(-cur, dp[i - 1][1]);
        }

        return dp[n - 1][0];
    }

    // Monotony Stack - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        Deque<Integer> stack = new LinkedList<>();

        int profit = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peekLast() >= prices[i]) {
                // 出栈
                int top = stack.pollLast();

                if (!stack.isEmpty()) {
                    // 更新答案
                    profit = Math.max(profit, top - stack.peekFirst());
                }
            }

            // 入栈
            stack.offerLast(prices[i]);
        }

        // 检验残余
        while (!stack.isEmpty() && stack.size() > 1) {
            profit = Math.max(profit, stack.pollLast() - stack.peekFirst());
        }

        return profit;
    }

}
