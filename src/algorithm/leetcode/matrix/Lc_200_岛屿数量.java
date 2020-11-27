package algorithm.leetcode.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Lc_200_岛屿数量 {

    // 岛屿数量
    // https://leetcode-cn.com/problems/number-of-islands/

    public static void main(String[] args) {
        Lc_200_岛屿数量 lc = new Lc_200_岛屿数量();
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};

        System.out.println("二维网格地图: ");
        for (char[] chars : grid) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println("中, 岛屿的数量为: " + lc.numIslandsBFS(grid));
    }

    // DFS - 时间复杂度 O(MN) - 空间复杂度 O(MN)
    public int numIslandsDFS(char[][] grid) {
        int res = 0, vertical = grid.length, horizontal = grid[0].length;

        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                // 当前块为陆地时,以当前块为中心dfs其上下左右
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);    // 上
        dfs(grid, i + 1, j);    // 下
        dfs(grid, i, j - 1);    // 左
        dfs(grid, i, j + 1);    // 右
    }

    // BFS - 时间复杂度 O(MN) - 空间复杂度 O(M+N)
    public int numIslandsBFS(char[][] grid) {
        int res = 0, vertical = grid.length, horizontal = grid[0].length;

        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                // 当前块为陆地时,以当前块为中心bfs其上下左右
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            i = cur[0];
            j = cur[1];
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                queue.offer(new int[]{i - 1, j});
                queue.offer(new int[]{i + 1, j});
                queue.offer(new int[]{i, j - 1});
                queue.offer(new int[]{i, j + 1});
            }
        }
    }

}
