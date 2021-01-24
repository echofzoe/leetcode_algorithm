package algorithm.leetcode.周赛.lc_2021_1_24_图灵教育;

import java.util.*;

public class Lc_5663_找出第K大的异或坐标值 {

    // 找出第 K 大的异或坐标值
    // https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/

    public static void main(String[] args) {
        Lc_5663_找出第K大的异或坐标值 lc = new Lc_5663_找出第K大的异或坐标值();
        // 10  9   5
        // 2   0   4
        // 1   0   9
        // 3   4   8
        int[][] matrix = {{10, 9, 5}, {2, 0, 4}, {1, 0, 9}, {3, 4, 8}};
        int K = 10;

        System.out.println(Arrays.deepToString(matrix) + "中第 " + K + " 大的异或坐标值是 " + lc.kthLargestValue(matrix, K));
    }

    // DP + 小根堆 - 时间复杂度 O(MN*log(MN)) - 空间复杂度 O(K)
    public int kthLargestValue(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;

        Queue<Integer> pq = new PriorityQueue<>() {{
            offer(matrix[0][0]);
        }};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                else if (i == 0) matrix[0][j] ^= matrix[0][j - 1];

                else if (j == 0) matrix[i][0] ^= matrix[i - 1][0];

                else matrix[i][j] = matrix[i - 1][j] ^ matrix[i][j - 1] ^ matrix[i - 1][j - 1] ^ matrix[i][j];

                pq.offer(matrix[i][j]);
                while (pq.size() > k) pq.poll();
            }
        }

        return pq.poll();
    }

}
