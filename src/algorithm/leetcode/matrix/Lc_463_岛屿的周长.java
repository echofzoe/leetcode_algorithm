package algorithm.leetcode.matrix;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Lc_463_岛屿的周长 {

    // 岛屿的周长
    // https://leetcode-cn.com/problems/island-perimeter/

    public static void main(String[] args) {
        Lc_463_岛屿的周长 lc = new Lc_463_岛屿的周长();
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};

        System.out.println("二维网格地图: ");
        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("中, 岛屿的周长为: " + lc.islandPerimeterIteration(grid));
    }

    // 遍历方向顺序 -> 右,下,左,上
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    // Iterate - 时间复杂度 O(vertical * horizontal) - 空间复杂度 O(1 )
    public int islandPerimeterIteration(int[][] grid) {
        int vertical = grid.length;
        int horizontal = grid[0].length;

        int res = 0;

        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= vertical || ty < 0 || ty >= horizontal || grid[tx][ty] == 0) cnt += 1;
                    }
                    res += cnt;
                }
            }
        }

        return res;
    }

    // DFS - 时间复杂度 O(vertical * horizontal) - 空间复杂度 O(vertical * horizontal) 为递归栈的开销
    public int islandPerimeterDFS(int[][] grid) {
        int vertical = grid.length;    // 纵向
        int horizontal = grid[0].length;    // 横向

        int res = 0;

        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                // 当前块为陆地时,以当前块为中心dfs其上下左右
                if (grid[i][j] == 1) {
                    res += dfs(i, j, grid, vertical, horizontal);
                }
            }
        }

        return res;
    }

    private int dfs(int x, int y, int[][] grid, int vertical, int horizontal) {
        if (x < 0 || x >= vertical || y < 0 || y >= horizontal || grid[x][y] == 0) return 1;

        if (grid[x][y] == 2) return 0;

        grid[x][y] = 2;    // 标记为已访问过

        int res = 0;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            res += dfs(tx, ty, grid, vertical, horizontal);
        }

        return res;
    }

}
