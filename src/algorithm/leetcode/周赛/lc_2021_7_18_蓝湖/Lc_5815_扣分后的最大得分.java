package algorithm.leetcode.周赛.lc_2021_7_18_蓝湖;

import java.util.Arrays;

/**
 * 扣分后的最大得分
 * <P>https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/</P>
 *
 * @author echofzoe
 * @since 2021.7.18
 */
public class Lc_5815_扣分后的最大得分 {

    public static void main(String[] args) {
        Lc_5815_扣分后的最大得分 lc = new Lc_5815_扣分后的最大得分();

        int[][] points = {{0, 3, 0, 4, 2}, {5, 4, 2, 4, 1}, {5, 0, 0, 5, 1}, {2, 0, 1, 0, 3}};

        System.out.println("给你一个 m x n 的整数矩阵 points （下标从 0 开始）。一开始你的得分为 0 ，你想最大化从矩阵中得到的分数。\n" +
                "你的得分方式为：每一行 中选取一个格子，选中坐标为 (r, c) 的格子会给你的总得分 增加 points[r][c] 。\n" +
                "然而，相邻行之间被选中的格子如果隔得太远，你会失去一些得分。对于相邻行 r 和 r + 1 （其中 0 <= r < m - 1），选中坐标为 (r, c1) 和 (r + 1, c2) 的格子，你的总得分 减少 abs(c1 - c2) 。\n" +
                "请你返回你能得到的 最大 得分。\n" +
                "abs(x) 定义为：\n" +
                "  - 如果 x >= 0 ，那么值为 x 。\n" +
                "  - 如果 x < 0 ，那么值为 -x 。\n");
        System.out.println("输入：points = " + Arrays.deepToString(points));
        System.out.println("输出：" + lc.maxPoints(points));  // 15
    }

    // DP - 时间复杂度 O(MN) - 空间复杂度 O(MN)
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;

        // dp[i][j] 表示第i行选择points[i][j]时的最大得分
        /*
            状态转移方程：{
                dp[i][j] = points[i][j] + max(dp[i - 1][k] - abs(k - j)), 0 <= k < n
                分解成:
                dp[i][j] = points[i][j] + max(dp[i - 1][k]) - (j - k), k <= j
                dp[i][j] = points[i][j] + max(dp[i - 1][k]) - (k - j), k > j
                化简成:
                dp[i][j] = points[i][j] - j + [max(dp[i - 1][k]) + k], k <= j
                dp[i][j] = points[i][j] + j + [max(dp[i - 1][k]) - k], k > j
            }
         */
        long[][] dp = new long[m][n];
        // base case
        for (int j = 0; j < n; j++) {
            dp[0][j] = points[0][j];
        }

        // 状态转移时，预处理 [max(dp[i - 1][k]) + k] when k <= j 和 [max(dp[i - 1][k]) - k] when k > j 的最大值
        for (int i = 1; i < m; i++) {
            long lmax = dp[i - 1][0] + 0;
            for (int j = 0; j < n; j++) {
                lmax = Math.max(lmax, dp[i - 1][j] + j);
                dp[i][j] = Math.max(dp[i][j], points[i][j] - j + lmax);
            }

            long rmax = dp[i - 1][n - 1] - (n - 1);
            for (int j = n - 1; j >= 0; j--) {
                rmax = Math.max(rmax, dp[i - 1][j] - j);
                dp[i][j] = Math.max(dp[i][j], points[i][j] + j + rmax);
            }
        }

        return Arrays.stream(dp[m - 1]).max().getAsLong();
    }

}
