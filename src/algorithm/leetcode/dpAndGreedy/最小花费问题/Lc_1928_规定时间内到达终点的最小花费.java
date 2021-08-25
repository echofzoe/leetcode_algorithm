package algorithm.leetcode.dpAndGreedy.最小花费问题;

import java.util.Arrays;

/**
 * 规定时间内到达终点的最小花费
 * <P>https://leetcode-cn.com/problems/minimum-cost-to-reach-destination-in-time/</P>
 *
 * @author echofzoe
 * @since 2021.08.25
 */
public class Lc_1928_规定时间内到达终点的最小花费 {

    public static void main(String[] args) {
        Lc_1928_规定时间内到达终点的最小花费 lc = new Lc_1928_规定时间内到达终点的最小花费();

        int maxTime = 30;
        int[][] edges = {{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}};
        int[] passingFees = {5, 1, 2, 20, 20, 3};

        System.out.println("一个国家有 n 个城市，城市编号为 0 到 n - 1 ，题目保证 所有城市 都由双向道路 连接在一起 。道路由二维整数数组 edges 表示，其中 edges[i] = [xi, yi, timei] 表示城市 xi 和 yi 之间有一条双向道路，耗费时间为 timei 分钟。两个城市之间可能会有多条耗费时间不同的道路，但是不会有道路两头连接着同一座城市。\n" +
                "每次经过一个城市时，你需要付通行费。通行费用一个长度为 n 且下标从 0 开始的整数数组 passingFees 表示，其中 passingFees[j] 是你经过城市 j 需要支付的费用。\n" +
                "一开始，你在城市 0 ，你想要在 maxTime 分钟以内 （包含 maxTime 分钟）到达城市 n - 1 。旅行的 费用 为你经过的所有城市 通行费之和 （包括 起点和终点城市的通行费）。\n" +
                "给你 maxTime，edges 和 passingFees ，请你返回完成旅行的 最小费用 ，如果无法在 maxTime 分钟以内完成旅行，请你返回 -1 ");
        System.out.println("输入：maxTime = " + maxTime + ", edges = " + Arrays.deepToString(edges) + ", passingFees = " + Arrays.toString(passingFees));
        System.out.println("输出：" + lc.minCost(maxTime, edges, passingFees));  // 11
    }

    // dp
    // - 时间复杂度 O((N + M) * maxTimes) 其中M是edges数组的长度，初始化数组dp需要O(N * maxTimes)，动态规划需要O(M * maxTimes)
    // - 空间复杂度 O(N * maxTimes)
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;

        // f[t][i] 表示使用恰好t分钟到达城市i需要的最少通行费总和
        int[][] f = new int[maxTime + 1][n];
        // base case
        for (int t = 0; t <= maxTime; t++) Arrays.fill(f[t], 0x3f3f3f3f);
        f[0][0] = passingFees[0];

        for (int t = 1; t <= maxTime; t++) {
            for (int[] e : edges) {
                int i = e[0], j = e[1], cost = e[2];
                if (cost <= t) {
                    f[t][i] = Math.min(f[t][i], f[t - cost][j] + passingFees[i]);
                    f[t][j] = Math.min(f[t][j], f[t - cost][i] + passingFees[j]);
                }
            }
        }

        int res = 0x3f3f3f3f;
        for (int t = 1; t <= maxTime; t++) {
            res = Math.min(res, f[t][n - 1]);
        }

        return res == 0x3f3f3f3f ? -1 : res;
    }

}
