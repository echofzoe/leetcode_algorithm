package algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * 最大正方形
 * <P>https://leetcode-cn.com/problems/maximal-square/</P>
 * 
 * @author echofzoe 
 * @since 2021.7.27
 */
public class Lc_221_最大正方形 {

    public static void main(String[] args) {
        Lc_221_最大正方形 lc = new Lc_221_最大正方形();
        
        // 1 0 1 0 0
        // 1 0 1 1 1
        // 1 1 1 1 1
        // 1 0 0 1 0
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};

        System.out.println("在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。");
        System.out.println("输入：matrix = " + Arrays.deepToString(matrix));
        System.out.println("输出：" + lc.maximalSquare(matrix));  // 4
    }

    // DP - 时间复杂度 O(M*N) - 空间复杂度 O(M*N)
    public int maximalSquare(char[][] matrix) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || (n = matrix[0].length) == 0) return 0;

        // dp[i][j] 表示以matrix[i][j]为右下角的最大正方形的边长
        int[][] dp = new int[m + 1][n + 1];
        // base case
        // dp[i][j] = 1, i == 1 || j == 1

        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    if (i == 1 || j == 1) dp[i][j] = 1;
                    else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }

                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        return maxSide * maxSide;
    }

}
