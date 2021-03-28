package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_73_矩阵置零 {

    // 矩阵置零
    // https://leetcode-cn.com/problems/set-matrix-zeroes/

    public static void main(String[] args) {
        Lc_73_矩阵置零 lc = new Lc_73_矩阵置零();
        int[][] matrix1 = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix2 = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        System.out.println("给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。");

        System.out.println("输入：" + Arrays.deepToString(matrix1));
        lc.setZeroes1(matrix1);
        System.out.println("输出：" + Arrays.deepToString(matrix1));

        System.out.println("输入：" + Arrays.deepToString(matrix2));
        lc.setZeroes2(matrix2);
        System.out.println("输出：" + Arrays.deepToString(matrix2));
    }

    // 标记 - 时间复杂度 O(MN) - 空间复杂度 O(M + N)
    public void setZeroes1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length, n = matrix[0].length;

        boolean[] row = new boolean[m], col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 标记 + 优化 - 时间复杂度 O(MN) - 空间复杂度 O(1)
    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length, n = matrix[0].length;

        // 用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间
        // 但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 0
        // 因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 0
        boolean row0 = false, col0 = false;

        // 检查首列是否为0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }

        // 检查首行是否为0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                row0 = true;
                break;
            }
        }

        // 用首行和首列标识其余的行列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 用首行和首列更改其余的行列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 更改首行和首列
        if (col0) for (int i = 0; i < m; i++) matrix[i][0] = 0;
        if (row0) for (int j = 0; j < n; j++) matrix[0][j] = 0;
    }

}
