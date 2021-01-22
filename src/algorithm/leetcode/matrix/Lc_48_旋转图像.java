package algorithm.leetcode.matrix;

import java.util.Arrays;

public class Lc_48_旋转图像 {

    // 旋转图像
    // https://leetcode-cn.com/problems/rotate-image/


    // eg: i = 0, j = 1
    // 0,0          i,j          0,2          0,3
    //
    // 1,0          1,1          1,2          j,n-1-i
    //
    // n-1-j,i      2,1          2,2          2,3
    //
    // 3,0          3,1          n-1-i,n-1-j  3,3

    public static void main(String[] args) {
        Lc_48_旋转图像 lc = new Lc_48_旋转图像();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("原地旋转二维矩阵：");
        for (int[] a : matrix) System.out.println(Arrays.toString(a));
        System.out.println("的结果是：");
        lc.rotateInPlace(matrix);
        for (int[] a : matrix) System.out.println(Arrays.toString(a));
    }

    // 原地 - 时间复杂度 O() - 空间复杂度 O()
    public void rotateInPlace(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];

                matrix[i][j] = matrix[n - 1 - j][i];

                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];

                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];

                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    // BF - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public void rotateBf(int[][] matrix) {
        int n = matrix.length;
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[j][n - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(tmp[i], 0, matrix[i], 0, n);
        }
    }

}
