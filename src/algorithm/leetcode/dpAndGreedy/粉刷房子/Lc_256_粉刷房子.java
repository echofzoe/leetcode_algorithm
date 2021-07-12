package algorithm.leetcode.dpAndGreedy.粉刷房子;

import java.util.Arrays;

/**
 * 粉刷房子
 * <P>https://leetcode-cn.com/problems/paint-house/</P>
 *
 * @author echofzoe
 * @since 2021.7.12
 */
public class Lc_256_粉刷房子 {

    public static void main(String[] args) {
        Lc_256_粉刷房子 lc = new Lc_256_粉刷房子();

        int[][] costs = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};

        System.out.println("假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。\n" +
                "当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。\n" +
                "例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。\n" +
                "注意：\n" +
                "  - 所有花费均为正整数。\n");
        System.out.println("输入：costs = " + Arrays.deepToString(costs));
        System.out.println("输出：" + lc.minCost(costs));  // 10
    }

    // dp - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int minCost(int[][] costs) {
        int n = costs.length;

        // dp[i][j] 表示当粉刷到房子i时，将房子粉刷成j所代表的颜色（0表红，1蓝，2绿）所花费的最少成本
        /*
            状态转移方程：{
                1.当前选红色，状态由前一个状态中选择蓝色和绿色转移而来
                - dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + cur[0]
                2.当前选蓝色，状态由前一个状态中选择红色和绿色转移而来
                - dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + cur[1]
                3.当前选绿色，状态由前一个状态中选择蓝色和红色转移而来
                - dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + cur[2]
            }
         */
        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            int[] cur = costs[i - 1];

            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cur[0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cur[1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cur[2];
        }

        return Arrays.stream(dp[n]).min().orElse(0);
    }

}
