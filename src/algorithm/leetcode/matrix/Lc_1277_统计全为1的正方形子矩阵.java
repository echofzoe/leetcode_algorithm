package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_1277_统计全为1的正方形子矩阵 {

    // 统计全为 1 的正方形子矩阵
    // https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/

    public static void main(String[] args) {
        Lc_1277_统计全为1的正方形子矩阵 lc = new Lc_1277_统计全为1的正方形子矩阵();
        // 0 1 1 1
        // 1 1 1 1
        // 0 1 1 1
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};

        System.out.println("只含0和1的二维矩阵" + Arrays.deepToString(matrix) + "中，完全由1组成的正方形子矩阵的个数为" + lc.countSquares(matrix));
    }

    // DP - 时间复杂度 O(M*N) - 空间复杂度 O(M*N)
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    }
                }

                res += dp[i][j];
            }
        }

        return res;
    }

}
