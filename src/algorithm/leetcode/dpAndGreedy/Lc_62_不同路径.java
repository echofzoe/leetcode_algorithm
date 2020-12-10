package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_62_不同路径 {

    // 不同路径
    // https://leetcode-cn.com/problems/unique-paths/

    public static void main(String[] args) {
        Lc_62_不同路径 lc = new Lc_62_不同路径();
        int m = 3, n = 7;

        System.out.println("机器人从" + m + "行" + n + "列的网格的左上角走到右下角共有" + lc.uniquePathsDp(m, n) + "条路径");
    }

    // DFS - 时间复杂度 O() - 空间复杂度 O()
    public int uniquePathsDfs(int m, int n) {
        int[][] memo = new int[m][n];

        return dfs(m, n, 0, 0, memo);
    }

    private int[][] directions = {{1, 0}, {0, 1}};

    /**
     * dfs algorithm
     *
     * @param m    网格纵深
     * @param n    网格横深
     * @param x    当前纵坐标
     * @param y    当前横坐标
     * @param memo 记忆化数组
     * @return 路径数
     */
    private int dfs(int m, int n, int x, int y, int[][] memo) {
        // boundary
        if (x >= m || y >= n) return 0;

        // hit
        if (x == m - 1 && y == n - 1) return 1;

        int sum = 0;
        for (int[] direction : directions) {
            int x1 = x + direction[0];
            int y1 = y + direction[1];
            sum += dfs(m, n, x1, y1, memo);
        }

        memo[x][y] = sum;

        return sum;
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可优化为O(1)
    public int uniquePathsDp(int m, int n) {
        int[][] dp = new int[m][n];

        // initialize all nodes as boundary 1
        // actually, just set nodes in [0, m) and [0, n) as boundary 1 is ok
        // the following which set all nodes is just for convenience
        int[] tmp = new int[n];
        Arrays.fill(tmp, 1);
        Arrays.fill(dp, tmp);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

}
