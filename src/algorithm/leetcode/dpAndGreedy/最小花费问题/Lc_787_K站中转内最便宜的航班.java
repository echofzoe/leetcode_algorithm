package algorithm.leetcode.dpAndGreedy.最小花费问题;

import java.util.Arrays;

/**
 * K 站中转内最便宜的航班
 * <P>https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/</P>
 *
 * @author echofzoe
 * @since 2021.08.25
 */
public class Lc_787_K站中转内最便宜的航班 {

    public static void main(String[] args) {
        Lc_787_K站中转内最便宜的航班 lc = new Lc_787_K站中转内最便宜的航班();

        int n = 3, src = 0, dst = 2, k = 1;
        int[][] edges = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};

        System.out.println("有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。\n" +
                "现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。\n");
        System.out.println("输入：n = " + n + ", edges = " + Arrays.deepToString(edges) + ", src = " + src + ", dst = " + dst + ", k = " + k);
        System.out.println("输出：" + lc.findCheapestPrice(n, edges, src, dst, k));  // 200
    }

    // dp - 时间复杂度 O() - 空间复杂度 O()
    // 注意：最多进行k次中转，即最多能够乘坐k+1次航班
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // f[i][j] 表示经过恰好i次航班，从出发站src到达i站需要的最小花费
        int[][] f = new int[k + 2][n];
        // base case
        for (int t = 0; t < k + 2; t++) Arrays.fill(f[t], 0x3f3f3f3f);
        f[0][src] = 0;

        for (int t = 1; t < k + 2; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }

        int res = 0x3f3f3f3f;
        for (int t = 1; t < k + 2; t++) {
            res = Math.min(res, f[t][dst]);
        }

        return res == 0x3f3f3f3f ? -1 : res;
    }

}
