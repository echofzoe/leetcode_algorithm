package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_1504_统计全1子矩形 {

    // 统计全 1 子矩形
    // https://leetcode-cn.com/problems/count-submatrices-with-all-ones/

    public static void main(String[] args) {
        Lc_1504_统计全1子矩形 lc = new Lc_1504_统计全1子矩形();
        // 0 1 1 0   ==>   0 1 2 0
        // 0 1 1 1   ==>   0 1 2 3
        // 1 1 1 0   ==>   1 2 3 0
        int[][] mat = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};

        System.out.println("只含0和1的二维矩阵" + Arrays.deepToString(mat) + "中，完全由1组成的子矩阵的个数为" + lc.numSubmat(mat));
    }

    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int m = mat.length, n = mat[0].length;

        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i][j - 1] + 1;
                    }

                    int min = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        min = Math.min(min, dp[k][j]);

                        if (min == 0) break;

                        res += min;
                    }
                }
            }
        }

        return res;
    }

}
