package algorithm.leetcode.array;

import java.util.Arrays;

public class Jzo_4_二维数组中的查找 {

    // 二维数组中的查找
    // https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/

    public static void main(String[] args) {
        Jzo_4_二维数组中的查找 lc = new Jzo_4_二维数组中的查找();

        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 5;

        System.out.println("二维数组" + Arrays.deepToString(matrix) + "中" + (lc.findNumberIn2DArrayLinear(matrix, target) ? "包含" : "不包含") + "整数 " + target);
    }

    // 时间复杂度 O(N*M) - 空间复杂度 O(1)
    public boolean findNumberIn2DArrayBruteForce(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int rows = matrix.length, columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    // 时间复杂度 O(N+M) - 空间复杂度 O(1)
    public boolean findNumberIn2DArrayLinear(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int rows = matrix.length, columns = matrix[0].length;

        int row = rows - 1, column = 0;

        while (row >= 0 && column < columns) {
            int cur = matrix[row][column];

            if (cur > target) row--;
            else if (cur < target) column++;
            else return true;
        }

        return false;
    }

}
