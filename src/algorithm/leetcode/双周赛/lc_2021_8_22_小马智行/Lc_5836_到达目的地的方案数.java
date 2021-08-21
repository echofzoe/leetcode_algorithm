package algorithm.leetcode.双周赛.lc_2021_8_22_小马智行;

import java.util.*;

/**
 * 到达目的地的方案数
 * <P>https://leetcode-cn.com/problems/number-of-ways-to-arrive-at-destination/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_5836_到达目的地的方案数 {

    public static void main(String[] args) {
        Lc_5836_到达目的地的方案数 lc = new Lc_5836_到达目的地的方案数();

        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};

        System.out.println("你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。\n" +
                "给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。\n" +
                "请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 1e9 + 7 取余 后返回。\n");
        System.out.println("输入：n = " + n + ", roads = " + Arrays.deepToString(roads));
        System.out.println("输出：" + lc.countPaths(n, roads));  // 4
    }

    // dijkstra - 时间复杂度 O(E*logE) E为图中边数
    public int countPaths(int n, int[][] roads) {
        if (roads.length == 0) return 1;

        final int MOD = (int) 1e9 + 7;

        // 构图
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int[] r : roads) {
            int a = r[0], b = r[1], c = r[2];
            g.computeIfAbsent(a, key -> new ArrayList<>()).add(new int[]{b, c});
            g.computeIfAbsent(b, key -> new ArrayList<>()).add(new int[]{a, c});
        }

        // dist[i][0] 为源点到i点的最低花费，dist[i][1] 为此最低花费的数目
        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) dist[i][0] = Integer.MAX_VALUE;
        dist[0][0] = 0;
        dist[0][1] = 1;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // pq.offer(new int[]{源点到点x的最低花费, 点x})
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int path = poll[0], x = poll[1];

            if (dist[x][0] < path) continue;

            for (int[] e : g.get(x)) {
                int y = e[0], d = dist[x][0] + e[1];

                if (d == dist[y][0]) {
                    dist[y][1] += dist[x][1];
                    dist[y][1] %= MOD;
                } else if (d < dist[y][0]) {
                    dist[y][0] = d;
                    dist[y][1] = dist[x][1];

                    pq.offer(new int[]{d, y});
                }
            }

        }

        return dist[n - 1][1];
    }

}
