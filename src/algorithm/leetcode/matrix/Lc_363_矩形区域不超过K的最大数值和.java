package algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * 矩形区域不超过 K 的最大数值和
 * <P>https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/</P>
 *
 * @author echofzoe
 * @since 2021.4.22
 */
public class Lc_363_矩形区域不超过K的最大数值和 {

    public static void main(String[] args) {
        Lc_363_矩形区域不超过K的最大数值和 lc = new Lc_363_矩形区域不超过K的最大数值和();

        int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
        int k = 2;

        System.out.println("矩阵" + Arrays.deepToString(matrix) + "内部矩形区域的不超过" + k + "的最大数值和是" + lc.maxSumSubmatrix(matrix, k));  // 2
    }

    // 二维前缀和 - 时间复杂度 O(N^2 * M^2) - 空间复杂度 O(N * M)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n, m;
        if (matrix == null || (n = matrix.length) == 0 || (m = matrix[0].length) == 0) return 0;

        int[][] a = new int[n + 1][m + 1];  // 二维前缀和数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int p = i; p <= n; p++) {
                    for (int q = j; q <= m; q++) {
                        int cur = a[p][q] - a[i - 1][q] - a[p][j - 1] + a[i - 1][j - 1];
                        if (cur <= k) res = Math.max(res, cur);
                    }
                }
            }
        }

        return res;
    }

}
