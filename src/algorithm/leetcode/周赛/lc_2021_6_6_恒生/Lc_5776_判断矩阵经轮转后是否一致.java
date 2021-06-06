package algorithm.leetcode.周赛.lc_2021_6_6_恒生;

import java.util.Arrays;

/**
 * 判断矩阵经轮转后是否一致
 * <P>https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/</P>
 *
 * @author echofzoe
 * @since 2021.6.6
 */
public class Lc_5776_判断矩阵经轮转后是否一致 {

    public static void main(String[] args) {
        Lc_5776_判断矩阵经轮转后是否一致 lc = new Lc_5776_判断矩阵经轮转后是否一致();

        int[][] mat = {{0, 1}, {1, 1}}, target = {{1, 0}, {0, 1}};

        System.out.println("给你两个大小为 n x n 的二进制矩阵 mat 和 target 。现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。");
        System.out.println("输入：mat = " + Arrays.deepToString(mat) + ", target = " + Arrays.deepToString(target));
        System.out.println("输出：" + lc.findRotation(mat, target));  // false
    }

    // 模拟 - 时间复杂度 O(N^2) - 空间复杂度 O(1) 原地操作
    private int n;

    public boolean findRotation(int[][] mat, int[][] target) {
        this.n = mat.length;

        for (int i = 0; i < 4; i++) {
            if (match(mat, target)) return true;

            rotate(mat);
        }

        return false;
    }

    private void rotate(int[][] mat) {
        // 原地旋转递推式: mat[i][j] = mat[n - 1 - j][i]
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = mat[i][j];

                mat[i][j] = mat[n - 1 - j][i];

                mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];

                mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];

                mat[j][n - 1 - i] = tmp;
            }
        }
    }

    private boolean match(int[][] mat, int[][] target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) return false;
            }
        }

        return true;
    }

}
