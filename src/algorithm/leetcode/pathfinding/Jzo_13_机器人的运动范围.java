package algorithm.leetcode.pathfinding;

import org.junit.Test;

public class Jzo_13_机器人的运动范围 {

    // 机器人的运动范围
    // https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

    public static void main(String[] args) {
        Jzo_13_机器人的运动范围 lc = new Jzo_13_机器人的运动范围();

        int m = 2, n = 3, k = 1;

        System.out.println(m + " 行 " + n + " 列的方格在限定数位和 k = " + k + " 的情况下，机器人能够到达 " + lc.movingCount(m, n, k) + " 个格子.");
    }

    public int movingCount(int m, int n, int k) {

        visited = new boolean[m][n];

        return dfs(m, n, k, 0, 0);
    }

    boolean[][] visited;

    // 注: 搜索条件可缩减为只向下或者向右搜索
    private int dfs(int m, int n, int k, int i, int j) {
        if (i > m - 1 || i < 0 || j > n - 1 || j < 0 || calculateBitSum(i, j) > k || visited[i][j]) return 0;

        visited[i][j] = true;

        return 1 + dfs(m, n, k, i + 1, j) + dfs(m, n, k, i, j + 1) + dfs(m, n, k, i - 1, j) + dfs(m, n, k, i, j - 1);
    }

    private int calculateBitSum(int i, int j) {
        int res = 0;

        while (i != 0) {
            res += i % 10;
            i /= 10;
        }

        while (j != 0) {
            res += j % 10;
            j /= 10;
        }

        return res;
    }
}
