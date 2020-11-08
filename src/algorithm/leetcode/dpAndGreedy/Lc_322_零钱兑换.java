package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_322_零钱兑换 {

    // 零钱兑换
    // https://leetcode-cn.com/problems/coin-change/

    public static void main(String[] args) {
        Lc_322_零钱兑换 lc = new Lc_322_零钱兑换();
        int[] coins = {1, 2, 5};
        int amount1 = 11;
        int amount2 = 111;
        int amount3 = 1111;

        System.out.println("最少使用" + lc.coinChangeRecursive(coins, amount1) + "枚面额在" + Arrays.toString(coins) + "里的硬币可以凑成总金额" + amount1);
        System.out.println("最少使用" + lc.coinChangeRecursiveWithMemory(coins, amount2) + "枚面额在" + Arrays.toString(coins) + "里的硬币可以凑成总金额" + amount2);
        System.out.println("最少使用" + lc.coinChangeDp(coins, amount3) + "枚面额在" + Arrays.toString(coins) + "里的硬币可以凑成总金额" + amount3);
    }

    int[] coins;
    int[] memo;

    // 暴力递归 - 时间复杂度 O(N^k) k为coins的长度 - 空间复杂度 O(N) 为递归栈的开销
    public int coinChangeRecursive(int[] coins, int amount) {
        // initialize
        this.coins = coins;

        return recur(amount);
    }

    private int recur(int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subProblem = recur(amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, 1 + subProblem);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 递归 + 备忘录 自顶向下 - 时间复杂度 O(N*k) k为coins的长度 - 空间复杂度 O(N) 为递归栈的开销
    public int coinChangeRecursiveWithMemory(int[] coins, int amount) {
        // initialize
        this.coins = coins;
        this.memo = new int[amount + 1];

        return recurWithMemo(amount);
    }

    private int recurWithMemo(int amount) {
        // base case
        if (amount < 0) return -1;
        if (amount < 1) return 0;

        if (memo[amount] != 0) return memo[amount];

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subProblem = recurWithMemo(amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, 1 + subProblem);
        }

        // 计入备忘录
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;

        return memo[amount];
    }

    // dp + 迭代 自底向上 - 时间复杂度 O(N*k) k为coins的长度 - 空间复杂度 O(1)
    public int coinChangeDp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;

        // 遍历所有状态
        for (int i = 0; i < dp.length; i++) {
            // 求所有选择的最小值
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
