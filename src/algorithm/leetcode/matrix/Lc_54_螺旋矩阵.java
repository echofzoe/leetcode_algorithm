package algorithm.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_54_螺旋矩阵 {

    // 螺旋矩阵
    // https://leetcode-cn.com/problems/spiral-matrix/

    public static void main(String[] args) {
        Lc_54_螺旋矩阵 lc = new Lc_54_螺旋矩阵();
        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix2 = {{7}, {9}, {6}};

        System.out.println("螺旋输出矩阵" + Arrays.deepToString(matrix1) + "的结果是" + lc.spiralOrder(matrix1));
        System.out.println("螺旋输出矩阵" + Arrays.deepToString(matrix2) + "的结果是" + lc.spiralOrder(matrix2));
    }

    // 模拟 - 时间复杂度 O(M*N) - 空间复杂度 O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;

        int m = matrix.length, n = matrix[0].length;

        int top = 0, left = 0;
        int bottom = m - 1, right = n - 1;
        int total = m * n;

        while (res.size() < total) {
            for (int col = left; col <= right; col++) {
                res.add(matrix[top][col]);
            }

            for (int row = top + 1; row <= bottom; row++) {
                res.add(matrix[row][right]);
            }

            // 只有一行或一列时，按第一个for循环的行或列遍历
            if (left < right && top < bottom) {
                for (int col = right - 1; col >= left; col--) {
                    res.add(matrix[bottom][col]);
                }

                for (int row = bottom - 1; row > top; row--) {
                    res.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return res;
    }

}
