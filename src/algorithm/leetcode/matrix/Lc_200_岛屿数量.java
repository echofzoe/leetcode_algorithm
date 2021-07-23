package algorithm.leetcode.matrix;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 * <P>https://leetcode-cn.com/problems/number-of-islands/</P>
 *
 * @author echofzoe
 * @updated 2021.7.22
 * @since unknown
 */
public class Lc_200_岛屿数量 {

    public static void main(String[] args) {
        Lc_200_岛屿数量 lc = new Lc_200_岛屿数量();

        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};

        System.out.println("给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。\n" +
                "岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。\n" +
                "此外，你可以假设该网格的四条边均被水包围。\n");
        System.out.println("输入：grid = " + Arrays.deepToString(grid));
        System.out.println("输出：" + lc.numIslandsBFS(grid));  // 3
    }

    // DFS - 时间复杂度 O(MN) - 空间复杂度 O(MN)
    private char[][] grid;
    private int m, n;

    public int numIslandsDFS(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (grid[i][j] == '0') return;

        grid[i][j] = '0';

        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            dfs(x, y);
        }
    }

    // BFS - 时间复杂度 O(MN) - 空间复杂度 O(M+N)
    public int numIslandsBFS(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void bfs(int i, int j) {
        Deque<int[]> d = new LinkedList<>();
        d.offerLast(new int[]{i, j});

        while (!d.isEmpty()) {
            int[] cur = d.pollFirst();
            i = cur[0];
            j = cur[1];

            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') continue;

            grid[i][j] = '0';

            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                d.offerLast(new int[]{x, y});
            }
        }
    }

}
