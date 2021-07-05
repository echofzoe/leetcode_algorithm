package algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * 搜索二维矩阵 II
 * <P>https://leetcode-cn.com/problems/search-a-2d-matrix-ii/</P>
 *
 * @author echofzoe
 * @since 2021.7.5
 */
public class Lc_240_搜索二维矩阵_II {

    public static void main(String[] args) {
        Lc_240_搜索二维矩阵_II lc = new Lc_240_搜索二维矩阵_II();

        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;

        System.out.println("编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：\n" +
                "  - 每行的元素从左到右升序排列。\n" +
                "  - 每列的元素从上到下升序排列。\n");
        System.out.println("输入：" + Arrays.deepToString(matrix) + ", target = " + target);
        System.out.println("输出：" + lc.searchMatrix(matrix, target));
    }

    // 二分查找 - 时间复杂度 O(log(m+n)) - 空间复杂度 O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;

        // iterate over matrix diagonals
        int shortDim = Math.min(m, n);
        for (int i = 0; i < shortDim; i++) {
            boolean horizontal = binarySearch(matrix, target, i, false);
            boolean vertical = binarySearch(matrix, target, i, true);

            if (horizontal || vertical) return true;
        }

        return false;
    }

    private boolean binarySearch(int[][] matrix, int t, int start, boolean isVertical) {
        int l = start, r = isVertical ? matrix.length - 1 : matrix[0].length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (isVertical) {
                if (matrix[m][start] < t) {
                    l = m + 1;
                } else if (matrix[m][start] > t){
                    r = m - 1;
                } else {
                    return true;
                }
            } else {
                if (matrix[start][m] < t) {
                    l = m + 1;
                } else if (matrix[start][m] > t){
                    r = m - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

}
