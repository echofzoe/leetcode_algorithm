package algorithm.leetcode.stack.monotonyStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Lc_85_最大矩形 {

    // 最大矩形
    // https://leetcode-cn.com/problems/maximal-rectangle/

    public static void main(String[] args) {
        Lc_85_最大矩形 lc = new Lc_85_最大矩形();
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};

        System.out.println("二维二进制矩阵: ");
        for (char[] chars : matrix) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println("中，只包含1的最大矩阵的面积是" + lc.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peekFirst()][j] >= left[i][j]) stack.pollFirst();

                up[i] = stack.isEmpty() ? -1 : stack.peekFirst();
                stack.push(i);
            }
            
            stack.clear();

            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peekFirst()][j] >= left[i][j]) stack.pop();

                down[i] = stack.isEmpty() ? m : stack.peekFirst();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                res = Math.max(res, area);
            }
        }

        return res;
    }

}
