package algorithm.leetcode.双周赛.lc_2021_8_22_小马智行;

import java.util.Arrays;

/**
 * 最大方阵和
 * <P>https://leetcode-cn.com/problems/maximum-matrix-sum/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_5835_最大方阵和 {

    public static void main(String[] args) {
        Lc_5835_最大方阵和 lc = new Lc_5835_最大方阵和();

        int[][] matrix = {{1, -1}, {-1, 1}};

        System.out.println("给你一个 n x n 的整数方阵 matrix 。你可以执行以下操作 任意次 ：\n" +
                "  - 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。\n" +
                "如果两个元素有 公共边 ，那么它们就是 相邻 的。\n" +
                "你的目的是 最大化 方阵元素的和。请你在执行以上操作之后，返回方阵的 最大 和。\n");
        System.out.println("输入：matrix = " + Arrays.deepToString(matrix));
        System.out.println("输出：" + lc.maxMatrixSum(matrix));  // 4
    }

    // 贪心 - 时间复杂度 O(MN) - 空间复杂度 O(1)
    public long maxMatrixSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int neg = 0, min = Integer.MAX_VALUE;
        long sum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = matrix[i][j], abs = Math.abs(cur);

                sum += abs;
                min = Math.min(min, abs);

                if (cur <= 0) neg++;
            }
        }

        return (neg & 1) == 0 ? sum : sum - 2L * min;
    }

}
