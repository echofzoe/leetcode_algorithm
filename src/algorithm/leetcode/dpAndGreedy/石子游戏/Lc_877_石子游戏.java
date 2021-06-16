package algorithm.leetcode.dpAndGreedy.石子游戏;

import java.util.Arrays;

/**
 * 石子游戏
 * <P>经典区间DP问题</P>
 * <P>https://leetcode-cn.com/problems/stone-game/</P>
 *
 * @author echofzoe
 * @since 2021.6.16
 */
public class Lc_877_石子游戏 {

    public static void main(String[] args) {
        Lc_877_石子游戏 lc = new Lc_877_石子游戏();

        int[] piles = {5, 3, 4, 5};

        System.out.println("亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。\n" +
                "游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。\n" +
                "亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。\n" +
                "假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。\n");
        System.out.println("输入：piles = " + Arrays.toString(piles));
        System.out.println("输出：" + lc.stoneGame(piles));
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        // dp[i][j] 表示在piles中剩下区间[i,j]的石子时，先手玩家Alex和后手玩家Lee之间的石子数的差的最大值
        /*
            边界条件：{
                1. 区间无效时，差值无效
                - dp[i][j] = 0, i > j
                2. 区间长度为1时，差值确定
                - dp[i][i] = piles[i]
            }
            状态转移方程：{
                1. 先手玩家选择最左石头piles[i]时，区间[i+1,j]中的石头只能由后手玩家选择，
                    则dp[i+1][j]表示区间内后手玩家比先手玩家多的石子数，
                    那么反过来，-dp[i+1][j]表示区间内先手玩家比后手玩家多的石子数；
                    先手玩家选择最右石头piles[j]时的分析原理同上
                - dp[i][j] = max(piles[i] + (-dp[i + 1][j]), piles[j] + (-dp[i][j - 1]))
            }
         */
        int[][] dp = new int[n][n];
        // base case
        // dp[i][j] = 0, i > j
        for (int i = 0; i < n; i++) dp[i][i] = i;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(piles[i] + (-dp[i + 1][j]), piles[j] + (-dp[i][j - 1]));
            }
        }

        return dp[0][n - 1] > 0;
    }

    // DP 空间优化 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public boolean stoneGame1(int[] piles) {
        int n = piles.length;

        int[] dp = new int[n];
        // base case
        // dp[i][j] = 0, i > j
        for (int i = 0; i < n; i++) dp[i] = i;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(piles[i] + (-dp[j]), piles[j] + (-dp[j - 1]));
            }
        }

        return dp[n - 1] > 0;
    }

}
