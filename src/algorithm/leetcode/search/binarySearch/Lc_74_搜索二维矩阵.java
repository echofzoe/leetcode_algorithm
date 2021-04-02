package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * 搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class Lc_74_搜索二维矩阵 {

    public static void main(String[] args) {
        Lc_74_搜索二维矩阵 lc = new Lc_74_搜索二维矩阵();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 13;

        System.out.println(Arrays.deepToString(matrix) + " 中" + (lc.searchMatrix(matrix, target) ? "存在 " : "不存在 ") + target);
    }

    // 两次二分 - 时间复杂度 O(logM + logN) = O(logMN) - 空间复杂度 O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length, n = matrix[0].length;

        int mid;

        // 二分行
        int lo = 0, hi = m - 1;
        while (lo < hi) {
            mid = lo + (hi - lo + 1) / 2;

            if (matrix[mid][0] > target) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }
        int row = lo;

        // 二分列
        lo = 0;
        hi = n - 1;
        while (lo < hi) {
            mid = lo + (hi - lo + 1) / 2;

            if (matrix[row][mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }
        int col = lo;

        return matrix[row][col] == target;
    }

}
