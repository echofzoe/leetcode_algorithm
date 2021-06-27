package algorithm.leetcode.周赛.lc_2021_6_27_恒生;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 循环轮转矩阵
 * <P>https://leetcode-cn.com/problems/cyclically-rotating-a-grid/</P>
 *
 * @author echofzoe
 * @since 2021.6.27
 */
public class Lc_5798_循环轮转矩阵 {

    public static void main(String[] args) {
        Lc_5798_循环轮转矩阵 lc = new Lc_5798_循环轮转矩阵();

        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int k = 2;

        System.out.println("给你一个大小为 m x n 的整数矩阵 grid，其中 m 和 n 都是 偶数 ; 另给你一个整数 k 。" +
                "矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。" +
                "返回执行 k 次循环轮转操作后的矩阵。\n");
        System.out.println("输入：grid = " + Arrays.deepToString(grid) + ", k = " + k);
        System.out.println("输出：" + Arrays.deepToString(lc.rotateGrid(grid, k)));
    }

    // 模拟 - 时间复杂度 O(m * n) - 空间复杂度 O(m * n)
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int circle = Math.min(m, n) / 2;

        for (int i = 0; i < circle; i++) {
            Deque<Integer> dq = new LinkedList<>();

            // 左->右
            for (int j = i; j < n - 1 - i; j++) dq.add(grid[i][j]);
            // 上->下
            for (int j = i; j < m - 1 - i; j++) dq.add(grid[j][n - 1 - i]);
            // 右->左
            for (int j = n - 1 - i; j > i; j--) dq.add(grid[m - 1 - i][j]);
            // 下->上
            for (int j = m - 1 - i; j > i; j--) dq.add(grid[j][i]);

            // 将list中的数据按题意旋转k次
            rotate(dq, k);

            int idx = 0;
            // 左->右
            for (int j = i; j < n - 1 - i; j++) grid[i][j] = dq.pollFirst();
            // 上->下
            for (int j = i; j < m - 1 - i; j++) grid[j][n - 1 - i] = dq.pollFirst();
            // 右->左
            for (int j = n - 1 - i; j > i; j--) grid[m - 1 - i][j] = dq.pollFirst();
            // 下->上
            for (int j = m - 1 - i; j > i; j--) grid[j][i] = dq.pollFirst();
        }

        return grid;
    }

    private void rotate(Deque<Integer> dq, int k) {
        int n = dq.size();
        k %= n;

        while (k-- > 0) {
            dq.add(dq.pollFirst());
        }
    }

}
