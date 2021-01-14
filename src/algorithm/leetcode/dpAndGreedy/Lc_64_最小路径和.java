package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_64_最小路径和 {

    // 最小路径和
    // https://leetcode-cn.com/problems/minimum-path-sum/

    public static void main(String[] args) {
        Lc_64_最小路径和 lc = new Lc_64_最小路径和();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println(Arrays.deepToString(grid) + "中从左上角到右下角的最小路径和是" + lc.minPathSum(grid));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }

        return grid[m - 1][n - 1];
    }

}
