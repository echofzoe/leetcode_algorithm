package algorithm.leetcode.array;

import java.util.Arrays;

public class Jzo_29_顺时针打印矩阵 {

    // 顺时针打印矩阵
    // https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/

    public static void main(String[] args) {
        Jzo_29_顺时针打印矩阵 lc = new Jzo_29_顺时针打印矩阵();
        int[][] input1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] input2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        System.out.println("矩阵" + Arrays.deepToString(input1) + "按照从外向里以顺时针的顺序依次打印出每一个数字的结果是" + Arrays.toString(lc.spiralOrder(input1)));
        System.out.println("矩阵" + Arrays.deepToString(input2) + "按照从外向里以顺时针的顺序依次打印出每一个数字的结果是" + Arrays.toString(lc.spiralOrder(input2)));
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, index = 0;
        int[] res = new int[(right + 1) * (bottom + 1)];
        while (true) {
            for (int i = left; i <= right; i++) res[index++] = matrix[top][i];    // left to right
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++) res[index++] = matrix[i][right];    // top to bottom
            if (--right < left) break;
            for (int i = right; i >= left; i--) res[index++] = matrix[bottom][i];    // right to left
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) res[index++] = matrix[i][left];    // bottom to top
            if (++left > right) break;
        }

        return res;
    }
}
