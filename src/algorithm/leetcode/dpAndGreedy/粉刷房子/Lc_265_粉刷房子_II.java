package algorithm.leetcode.dpAndGreedy.粉刷房子;

import java.util.Arrays;

/**
 * 粉刷房子 II
 * <P>https://leetcode-cn.com/problems/paint-house-ii/</P>
 *
 * @author echofzoe
 * @since 2021.7.12
 */
public class Lc_265_粉刷房子_II {

    public static void main(String[] args) {
        Lc_265_粉刷房子_II lc = new Lc_265_粉刷房子_II();

        int[][] costs = {{15, 17, 15, 20, 7, 16, 6, 10, 4, 20, 7, 3, 4}, {11, 3, 9, 13, 7, 12, 6, 7, 5, 1, 7, 18, 9}};

        System.out.println("假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。\n" +
                "当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。\n" +
                "例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。\n" +
                "注意：\n" +
                "  - 所有花费均为正整数。\n");
        System.out.println("输入：" + Arrays.deepToString(costs));
        System.out.println("输出：" + lc.minCostII(costs));  // 4
    }

    // dp - 时间复杂度 O(NK^2) - 空间复杂度 O(NK)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;

        // dp[i][j] 表示当粉刷到房子i时，将房子粉刷成j所代表的颜色所花费的最少成本
        /*
            状态转移方程：{
                1.当前选择颜色j，状态由前一个状态中选择非颜色j转移而来
                - dp[i][j] = min(dp[i - 1][x1], dp[i - 1][x2], ...) + cur[j],
                - 其中 1<=i<=n && 0<=j<k && 0<=(x1,x2,...)<k && cur=costs[i-1]
            }
         */
        int[][] dp = new int[n + 1][k];

        for (int i = 1; i <= n; i++) {
            int[] cur = costs[i - 1];
            int[] pre = dp[i - 1];

            for (int j = 0; j < k; j++) {
                int preIdx = 0, min = 0x3f3f3f3f;
                // 在前一个状态中选择非颜色j所花费的最小成本
                for (int x = 0; x < k; x++) {
                    if (j != x && pre[x] < min) {
                        preIdx = x;
                        min = pre[preIdx];
                    }
                }

                // 当前选择j的状态 由 前一个状态中 选择非j所花费的成本 中的最小成本更新而来
                dp[i][j] = dp[i - 1][preIdx] + cur[j];
            }
        }

        return Arrays.stream(dp[n]).min().orElse(0);
    }

}
