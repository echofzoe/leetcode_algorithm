package algorithm.leetcode.双周赛.lc_2021_5_29_图森未来;

import java.util.Arrays;

/**
 * 矩阵中最大的三个菱形和
 * <P>https://leetcode-cn.com/problems/get-biggest-three-rhombus-sums-in-a-grid/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_1878_矩阵中最大的三个菱形和 {

    public static void main(String[] args) {
        Lc_1878_矩阵中最大的三个菱形和 lc = new Lc_1878_矩阵中最大的三个菱形和();

        int[][] grid = {{3, 4, 5, 1, 3}, {3, 3, 4, 2, 3}, {20, 30, 200, 40, 10}, {1, 5, 5, 4, 1}, {4, 3, 2, 2, 5}};

        System.out.println("输入：grid = " + Arrays.deepToString(grid));
        System.out.println("输出：" + Arrays.toString(lc.getBiggestThree(grid)));

    }

    public int[] getBiggestThree(int[][] grid) {

    }

}
