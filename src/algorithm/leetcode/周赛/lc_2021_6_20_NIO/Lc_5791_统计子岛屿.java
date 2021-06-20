package algorithm.leetcode.周赛.lc_2021_6_20_NIO;

import java.util.Arrays;

/**
 * 统计子岛屿
 * <P>https://leetcode-cn.com/problems/count-sub-islands/</P>
 *
 * @author echofzoe
 * @since 2021.6.20
 */
public class Lc_5791_统计子岛屿 {

    public static void main(String[] args) {
        Lc_5791_统计子岛屿 lc = new Lc_5791_统计子岛屿();

        int[][] grid1 = {{0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 0}};
        int[][] grid2 = {{1, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0}};

        System.out.println("输入：grid1 = " + Arrays.deepToString(grid1) + ", grid2 = " + Arrays.deepToString(grid2));
        System.out.println("输出：" + lc.countSubIslands(grid1, grid2));
    }

    // DFS - 时间复杂度 O(m * n) - 空间复杂度 O(m * n) 为递归栈的深度
    private int[][] grid1, grid2;
    private int m, n;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean isDao;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        m = grid1.length;
        n = grid1[0].length;

        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    isDao = true;
                    dfs(i, j);
                    if (isDao) res++;
                }
            }
        }

        return res;
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (grid2[i][j] == 0) return;

        if (grid1[i][j] == 0) isDao = false;

        grid2[i][j] = 0;

        for (int[] dir : dirs) {
            int x = dir[0], y = dir[1];
            dfs(i + x, j + y);
        }
    }

}
