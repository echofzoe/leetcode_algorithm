package algorithm.leetcode.双周赛.lc_2021_7_10_贝壳;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 迷宫中离入口最近的出口
 * <P>https://leetcode-cn.com/problems/nearest-exit-from-entrance-in-maze/</P>
 *
 * @author echofzoe
 * @since 2021.7.11
 */
public class Lc_5793_迷宫中离入口最近的出口 {

    public static void main(String[] args) {
        Lc_5793_迷宫中离入口最近的出口 lc = new Lc_5793_迷宫中离入口最近的出口();

        char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        int[] entrance = {1, 2};

        System.out.println("给你一个 m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，用 entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列。\n" +
                "每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。出口 的含义是 maze 边界 上的 空格子。entrance 格子 不算 出口。\n" +
                "请你返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，请你返回 -1 。\n");
        System.out.println("输入：maze = " + Arrays.deepToString(maze) + ", entrance = " + Arrays.toString(entrance));
        System.out.println("输出：" + lc.nearestExit(maze, entrance));  // 1
    }

    // bfs - 时间复杂度 O(MN) 最坏情况下遍历完所有格子 - 空间复杂度 O(MN) 为记忆化数组vis的开销
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[][] maze;
    private int m, n;
    private boolean[][] vis;

    public int nearestExit(char[][] maze, int[] entrance) {
        this.maze = maze;
        m = maze.length;
        n = maze[0].length;
        vis = new boolean[m][n];

        return bfs(entrance[0], entrance[1], 0);
    }

    private int bfs(int r, int c, int step) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        vis[r][c] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];

                    // cut
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (vis[x][y] || maze[x][y] == '+') continue;

                    vis[x][y] = true;
                    // exit
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) return step;

                    q.offer(new int[]{x, y});
                }
            }
        }

        return -1;
    }

}
