package algorithm.leetcode.周赛.lc_2021_3_7_微策略;

import java.util.*;

public class Lc_5699_从第一个节点出发到最后一个节点的受限路径数 {

    // 从第一个节点出发到最后一个节点的受限路径数
    // https://leetcode-cn.com/problems/number-of-restricted-paths-from-first-to-last-node/

    public static void main(String[] args) {
        Lc_5699_从第一个节点出发到最后一个节点的受限路径数 lc = new Lc_5699_从第一个节点出发到最后一个节点的受限路径数();
        int n = 5;
        int[][] edges = {{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}};

        System.out.println("从节点1出发至节点n的满足题意的受限路径数（结果对10^9+7取余）是" + lc.countRestrictedPaths(n, edges));
    }

    // dijkstra + 拓扑排序 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    final int MOD = 1000000007;
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        // 初始化邻接表
        for (int[] e : edges) {
            int x = e[0], y = e[1], d = e[2];

            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[] {y, d});
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[] {x, d});
        }

        int[] dist = findShortestPath(graph, n, n);

        long[] mem = new long[n + 1];
        return (int) dfs(graph, 1, n, dist, mem);
    }

    // dijkstra - 时间复杂度 O(E*logE) E为图中边数
    private int[] findShortestPath(Map<Integer, List<int[]>> map, int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0x3f3f3f3f);
        // base case
        dist[start] = 0;
        dist[0] = 0;

        boolean[] vis = new boolean[n + 1];

        Queue<Integer> pq = new PriorityQueue<>((x, y) -> dist[x] - dist[y]);
        pq.offer(start);

        while (!pq.isEmpty()) {
            Integer poll = pq.poll();
            if (vis[poll]) continue;
            vis[poll] = true;

            // 遍历poll在邻接表中的所有邻居
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                if (vis[next]) continue;

                // 更新到这个邻居的最短距离，看看是不是当前poll出来的节点到他更近一点
                dist[next] = Math.min(dist[next], dist[poll] + arr[1]);
                pq.offer(next);
            }

        }

        return dist;
    }

    private long dfs(Map<Integer, List<int[]>> map, int start, int n, int[] dist, long[] mem) {
        if (start == n) return 1;
        if (mem[start] != 0) return mem[start];

        long res = 0;

        List<int[]> list = map.getOrDefault(start, Collections.emptyList());
        for (int[] arr : list) {
            int next = arr[0];
            if (dist[start] > dist[next]) {
                res += dfs(map, next, n, dist, mem);
                res %= MOD;
            }
        }

        mem[start] = res;
        return res;
    }

}
