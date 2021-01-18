package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_221_最大正方形 {

    // 最大正方形
    // https://leetcode-cn.com/problems/maximal-square/

    public static void main(String[] args) {
        Lc_221_最大正方形 lc = new Lc_221_最大正方形();
        // 1 0 1 0 0
        // 1 0 1 1 1
        // 1 1 1 1 1
        // 1 0 0 1 0
        int[][] matrix = {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};

        System.out.println("只含0和1的二维矩阵" + Arrays.deepToString(matrix) + "中，只包含1的最大正方形面积为" + lc.maximalSquare(matrix));
    }

    // DP - 时间复杂度 O(M*N) - 空间复杂度 O(M*N)
    public int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    }

                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return (int) Math.pow(maxSide, 2);
    }

}
