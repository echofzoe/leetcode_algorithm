package algorithm.leetcode.周赛.Lc_2020_12_27_RingCentral;

import java.util.Arrays;

public class Lc_5210_球会落何处 {

    // 球会落何处
    // https://leetcode-cn.com/problems/where-will-the-ball-fall/

    public static void main(String[] args) {
        Lc_5210_球会落何处 lc = new Lc_5210_球会落何处();
        int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, 1, 1, 1}, {1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1}};

        System.out.println("在二维网格表示的箱子: ");
        for (int[] g : grid) System.out.println(Arrays.toString(g));
        System.out.println("中，球从上方依次落下，落点的结果是" + Arrays.toString(lc.findBall(grid)));
    }

    public int[] findBall(int[][] grid) {
        int row = grid.length, column = grid[0].length;
        if (column == 1) return new int[] {-1};
        int[] res = new int[column];

        for (int i = 0; i < column; i++) res[i] = dfs(grid, column, row, i, 0);

        return res;
    }

    // x = 当前列, y = 当前行
    private int dfs(int[][] grid, int column, int row, int x, int y) {
        // 到达底部
        if (y == row) return x;

        // 卡在边缘
        if (x == 0 && grid[y][x] == -1) return -1;
        if (x == column - 1 && grid[y][x] == 1) return -1;

        // 卡在中间
        if (grid[y][x] == -1 && grid[y][x - 1] == 1) return -1;
        if (grid[y][x] == 1 && grid[y][x + 1] == -1) return -1;

        // 进入下一层
        return dfs(grid, column, row, x + grid[y][x], y + 1);
    }

}
