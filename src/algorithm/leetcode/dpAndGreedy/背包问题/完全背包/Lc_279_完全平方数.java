package algorithm.leetcode.dpAndGreedy.背包问题.完全背包;

import java.util.Arrays;

/**
 * 完全平方数
 * <P>https://leetcode-cn.com/problems/perfect-squares/</P>
 *
 * @author echofzoe
 * @since 2021.6.7
 */
public class Lc_279_完全平方数 {

    public static void main(String[] args) {
        Lc_279_完全平方数 lc = new Lc_279_完全平方数();

        int n1 = 12, n2 = 13;

        System.out.println("给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。\n" +
                "给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。\n" +
                "完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。\n");
        System.out.println("输入：n = " + n1);
        System.out.println("输出：" + lc.numSquares(n1));
        System.out.println("\n输入：n = " + n2);
        System.out.println("输出：" + lc.numSquares(n2));
    }

    // DP - 时间复杂度 O(N * √N) - 空间复杂度 O(N)
    public int numSquares(int n) {
        // 预处理候选的完全平方数
        int maxSquare = (int) Math.sqrt(n);
        int[] squareCandidates = new int[maxSquare + 1];
        for (int i = 1; i <= maxSquare; i++) {
            squareCandidates[i] = i * i;
        }

        // dp[i] 表示和为i的完全平方数的最少数量
        /*
            边界条件：{
                dp[0] = 0
            }
            状态转移方程：{
                dp[i] = dp[i - squareCandidates[j]] + 1, squareCandidates[j] < i && 1 <= j <= maxSquare
            }
         */
        int[] dp = new int[n + 1];
        // base case
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxSquare; j++) {
                if (squareCandidates[j] > i) break;

                dp[i] = Math.min(dp[i], dp[i - squareCandidates[j]] + 1);
            }
        }

        return dp[n];
    }

}
