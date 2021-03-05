package algorithm.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_304_二维区域和检索_矩阵不可变 {

    // 二维区域和检索 - 矩阵不可变
    // https://leetcode-cn.com/problems/range-sum-query-2d-immutable/

    public static void main(String[] args) {
        Lc_304_二维区域和检索_矩阵不可变 lc = new Lc_304_二维区域和检索_矩阵不可变();
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        String[] command = {"NumMatrix", "sumRegion", "sumRegion", "sumRegion"};
        List<Object> parameters = new ArrayList<>() {{
            add(matrix);
            add(new int[]{2, 1, 4, 3});
            add(new int[]{1, 1, 2, 2});
            add(new int[]{1, 2, 2, 4});
        }};

        System.out.println("在矩阵" + Arrays.deepToString(matrix) + "中，");
        NumMatrix2 nm = new NumMatrix2(matrix);
        for (int i = 1; i < command.length; i++) {
            int[] para = (int[]) parameters.get(i);
            System.out.println("从左上角[" + para[0] + "," + para[1] + "]到右下角[" + para[2] + "," + para[3] + "]的范围内元素的总和是"  + nm.sumRegion(para[0], para[1], para[2], para[3]));
        }
    }

}

// 一维前缀和做法 - 时间复杂度 O(MN) - 空间复杂度 O(MN) 其中M和N分别是矩阵matrix的行数和列数
class NumMatrix {

    int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;

            preSum = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += preSum[i][col2 + 1] - preSum[i][col1];
        }

        return sum;
    }
}

// 二维前缀和做法 - 时间复杂度 O(MN) - 空间复杂度 O(MN) 其中M和N分别是矩阵matrix的行数和列数
class NumMatrix2 {

    int[][] preSum;

    public NumMatrix2(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;

            preSum = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    }
}
