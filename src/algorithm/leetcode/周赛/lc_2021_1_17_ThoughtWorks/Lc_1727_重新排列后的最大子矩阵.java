package algorithm.leetcode.周赛.lc_2021_1_17_ThoughtWorks;

import java.util.Arrays;
import java.util.Map;

public class Lc_1727_重新排列后的最大子矩阵 {

    // 重新排列后的最大子矩阵
    // https://leetcode-cn.com/problems/largest-submatrix-with-rearrangements/

    public static void main(String[] args) {
        Lc_1727_重新排列后的最大子矩阵 lc = new Lc_1727_重新排列后的最大子矩阵();
        int[][] matrix = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};

        System.out.println("重排矩阵" + Arrays.deepToString(matrix) + "后得到的最大全1子矩阵的面积是" + lc.largestSubmatrix(matrix));
    }

    // 贪心 - 时间复杂度 O(M*N) - 空间复杂度 O(1)
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            Arrays.sort(matrix[i]);

            for (int j = n - 1; j >= 0; j--) {
                // 更新子矩阵的最大高度
                int height = matrix[i][j];
                // 更新子矩阵的最大面积
                res = Math.max(res, height * (n - j));
            }
        }

        return res;
    }

}
