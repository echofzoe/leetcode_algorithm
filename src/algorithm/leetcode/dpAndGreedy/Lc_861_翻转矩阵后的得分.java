package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_861_翻转矩阵后的得分 {

    // 翻转矩阵后的得分
    // https://leetcode-cn.com/problems/score-after-flipping-matrix/

    public static void main(String[] args) {
        Lc_861_翻转矩阵后的得分 lc = new Lc_861_翻转矩阵后的得分();
        /* -----------
           | 0 0 1 1 |
           | 1 0 1 0 |
           | 1 1 0 0 |
           ----------- */
        int[][] matrix = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};

        System.out.println("对矩阵" + Arrays.deepToString(matrix) + "进行翻转后的最高得分是 " + lc.matrixScore(matrix));
    }

    // 贪心 - 时间复杂度 O(vertical * horizontal) - 空间复杂度 O(1)
    public int matrixScore(int[][] A) {
        int vertical = A.length, horizontal = A[0].length;

        // 1. 第一列全部置1
        for (int i = 0; i < vertical; i++) {
            if (A[i][0] != 1) {
                for (int j = 0; j < horizontal; j++) {
                    A[i][j] = A[i][j] == 0 ? 1 : 0;
                }
            }
        }

        // 2. 从第二列开始，检查当前列中1的个数是否大于0的个数，若小于则翻转该列
        for (int i = 1; i < horizontal; i++) {
            int count = 0;

            for (int j = 0; j < vertical; j++) {
                if (A[j][i] == 1) count++;
            }

            if (count <= vertical / 2) {
                for (int j = 0; j < vertical; j++) {
                    A[j][i] = A[j][i] == 0 ? 1 : 0;
                }
            }
        }

        // 3. 计算结果
        int res = 0;
        for (int i = 0; i < horizontal; i++) {
            int count = 0;
            int power = (int) Math.pow(2, horizontal - 1 - i);

            for (int j = 0; j < vertical; j++) {
                if (A[j][i] == 1) count++;
            }

            res += count * power;
        }

        return res;
    }

    public int matrixScore1(int[][] A) {
        int vertical = A.length, horizontal = A[0].length;

        for (int[] a : A) {
            if (a[0] == 0) {
                for (int i = 0; i < horizontal; i++) {
                    a[i] = a[i] == 0 ? 1 : 0;
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < horizontal; i++) {
            int count = 0;
            for (int j = 0; j < vertical; j++) {
                if (A[j][i] == 1) count++;
            }
            if (count <= vertical / 2) count = vertical - count;
            res += count * Math.pow(2, horizontal - 1 - i);
        }

        return res;
    }

}
