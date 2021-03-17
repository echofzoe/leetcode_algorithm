package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_59_II_螺旋矩阵 {

    // 螺旋矩阵 II
    // https://leetcode-cn.com/problems/spiral-matrix-ii/

    public static void main(String[] args) {
        Lc_59_II_螺旋矩阵 lc = new Lc_59_II_螺旋矩阵();
        int n = 3;

        System.out.println("按顺时针螺旋排列的" + n + "x" + n + "正方形矩阵是" + Arrays.deepToString(lc.generateMatrix1(n)));
    }

    // 模拟 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0) return res;

        int top = 0, left = 0;
        int bottom = n - 1, right = n - 1;

        int cnt = 1;
        while (top <= bottom && left <= right) {
            // left to right
            for (int col = left; col <= right; col++) res[top][col] = cnt++;
            top++;

            // top to bottom
            for (int row = top; row <= bottom; row++) res[row][right] = cnt++;
            right--;

            // right to left
            for (int col = right; col >= left; col--) res[bottom][col] = cnt++;
            bottom--;

            // bottom to top
            for (int row = bottom; row >= top; row--) res[row][left] = cnt++;
            left++;
        }

        return res;
    }

    // 模拟 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int[][] generateMatrix1(int n) {
        int[][] res = new int[n][n];
        if (n == 0) return res;

        int top = 0, left = 0;
        int bottom = n - 1, right = n - 1;

        int cnt = 1;
        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) res[top][col] = cnt++;

            for (int row = top + 1; row <= bottom; row++) res[row][right] = cnt++;

            // 只有一行或一列时，按第一个for循环的行或列遍历
            if (left < right && top < bottom) {
                for (int col = right - 1; col >= left; col--) res[bottom][col] = cnt++;

                for (int row = bottom - 1; row > top; row--) res[row][left] = cnt++;
            }

            top++;
            right--;
            bottom--;
            left++;
        }

        return res;
    }

}
