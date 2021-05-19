package algorithm.leetcode.bit.xor;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 找出第 K 大的异或坐标值
 * <P>https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/</P>
 *
 * @author echofzoe
 * @since 2021.5.19
 */
public class Lc_1738_找出第K大的异或坐标值 {

    public static void main(String[] args) {
        Lc_1738_找出第K大的异或坐标值 lc = new Lc_1738_找出第K大的异或坐标值();

        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 2;

        System.out.println("给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成，且 1 <= m, n <= 1000。\n" +
                "矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。\n" +
                "请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。");
        System.out.println("输入：" + Arrays.deepToString(matrix));
        System.out.println("输出：" + lc.kthLargestValue(matrix, k));  // 5
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

        int[][] pre = new int[m + 1][n + 1];
        int[] arr = new int[m * n];
        int idx = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                arr[idx++] = pre[i][j];
            }
        }

        quickSort(arr);
        return arr[k - 1];
    }

    private void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo, j = hi + 1, pivot = arr[lo];
        while (true) {
            while (i < hi && arr[++i] >= pivot) ;
            while (j > lo && arr[--j] <= pivot) ;

            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j);

        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }

    private void swap(int[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

}
