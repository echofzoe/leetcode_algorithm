package algorithm.leetcode.graph.unionFindSet;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_778_水位上升的泳池中游泳 {

    // 水位上升的泳池中游泳
    // https://leetcode-cn.com/problems/swim-in-rising-water/

    public static void main(String[] args) {
        Lc_778_水位上升的泳池中游泳 lc = new Lc_778_水位上升的泳池中游泳();
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};

        System.out.println("从坐标平台" + Arrays.deepToString(grid) + "的左上角到右下角的最少耗时是 " + lc.swimInWater(grid));
    }

    // 并查集 - 时间复杂度 O(N^2*logN) 计数排序O(N^2)，合并四周、检查起点和终点是否同属于一个连通分量O(log(N^2)) - 空间复杂度 O(N^2)
    public int swimInWater(int[][] grid) {
        int n = grid.length, end = n * n;

        final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int[] index = new int[end];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                index[grid[i][j]] = i * n + j;
            }
        }

        UnionFindSet ufs = new UnionFindSet(n * n);

        for (int i = 0; i < end; i++) {
            int x = index[i] / n, y = index[i] % n;

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1 || grid[nx][ny] > i) continue;

                ufs.union(index[i], nx * n + ny);

                if (ufs.isConnected(0, end - 1)) return i;
            }
        }

        return -1;
    }

}
