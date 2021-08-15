package algorithm.leetcode.dfsAndBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 出界的路径数
 * <P>https://leetcode-cn.com/problems/out-of-boundary-paths/</P>
 *
 * @author echofzoe
 * @since 2021.8.15
 */
public class Lc_576_出界的路径数 {

    public static void main(String[] args) {
        Lc_576_出界的路径数 lc = new Lc_576_出界的路径数();

        int m = 45, n = 35, maxMove = 47, startRow = 20, startColumn = 31;

        System.out.println("给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。\n" +
                "给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。\n");
        System.out.println("输入：m = " + m + ", n = " + n + ", maxMove = " + maxMove + ", startRow = " + startRow + ", startColumn = " + startColumn);
        System.out.println("输出：" + lc.findPaths1(m, n, maxMove, startRow, startColumn));  // 951853874
    }

    // dfs - 时间复杂度 O(MN) - 空间复杂度 O(MN * maxMove)
    private int m, n;
    private int[][][] mem;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int MOD = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        mem = new int[m][n][maxMove + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(mem[i][j], -1);
            }
        }

        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int r, int c, int cnt) {
        // exit
        if (r < 0 || r >= m || c < 0 || c >= n) return 1;

        // pruning
        if (cnt == 0) return 0;
        if (mem[r][c][cnt] != -1) return mem[r][c][cnt];

        // dfs
        int ans = 0;
        for (int[] dir : dirs) {
            int x = r + dir[0], y = c + dir[1];
            ans = (ans + dfs(x, y, cnt - 1)) % MOD;
        }

        return mem[r][c][cnt] = ans;
    }

    // bfs - 时间复杂度 O() - 空间复杂度 O()
    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startRow << 8 | startColumn);

        int[][] mem = new int[m][n];
        // base case
        mem[startRow][startColumn] = 1;

        int res = 0;
        while (maxMove-- > 0) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                int r = cur >> 8, c = cur & 0xff;
                for (int[] dir : dirs) {
                    int x = r + dir[0], y = c + dir[1];

                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        res = (res + mem[r][c]) % MOD;
                        continue;
                    }

                    if (mem[x][y] == 0) q.offer(x << 8 | y);

                    mem[x][y] = (mem[x][y] + mem[r][c]) % MOD;
                }

                // 波纹bfs，更新完下一层后立即清除上一层的状态
                mem[r][c] = 0;
            }
        }

        return res;
    }

}
