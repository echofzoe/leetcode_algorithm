package algorithm.leetcode.graph.shortestPath;

import java.util.*;

/**
 * 网络延迟时间
 * <P>https://leetcode-cn.com/problems/network-delay-time/</P>
 * <P>题解：https://leetcode-cn.com/problems/network-delay-time/solution/java-ban-dijkstra-spfa-floydxiang-xi-ti-jie-by-jer/</P>
 * <P>博客：https://www.cnblogs.com/thousfeet/p/9229395.html</P>
 *
 * @author echofzoe
 * @since 2021.5.25
 */
public class Lc_743_网络延迟时间 {

    public static void main(String[] args) {
        Lc_743_网络延迟时间 lc = new Lc_743_网络延迟时间();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4, k = 2;

        System.out.println("times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间");
        System.out.println("则从节点" + k + "发出一个信号，需要" + lc.networkDelayTimeHeapSPFA2(times, n, k) + "个时间才能使所有节点都收到信号（若不能使所有节点收到则返回-1）");
    }

    // dijkstra + 堆优化 + 邻接表 - 时间复杂度 O(E*logE + V) E为图中边数,V为图中顶点数 - 空间复杂度 O(N)
    public int networkDelayTime(int[][] times, int n, int k) {
        // 构图 - 邻接表
        // <point1, {[point2, distance2], [point3, distance3], ...}>
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] t : times) {
            int x = t[0], y = t[1], d = t[2];
            graph.computeIfAbsent(x, key -> new ArrayList<>()).add(new int[]{y, d});
        }

        // 迪克斯特拉
        int[] dist = dijkstra(graph, n, k);

        int res = Arrays.stream(dist).max().orElse(0x3f3f3f3f);
        return res == 0x3f3f3f3f ? -1 : res;
    }

    // dijkstra - 时间复杂度 O(E*logE) E为图中边数
    private int[] dijkstra(Map<Integer, List<int[]>> graph, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0x3f3f3f3f);
        // base case
        dist[k] = dist[0] = 0;

        boolean[] vis = new boolean[n + 1];

        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> dist[x]));
        pq.offer(k);

        while (!pq.isEmpty()) {
            Integer poll = pq.poll();
            if (vis[poll]) continue;
            vis[poll] = true;

            // 遍历当前节点的邻居，实时更新最短路径
            List<int[]> list = graph.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                if (vis[next]) continue;

                dist[next] = Math.min(dist[next], dist[poll] + arr[1]);
                pq.offer(next);
            }
        }

        return dist;
    }

    // SPFA + 邻接表 - 时间复杂度 O(E) - 空间复杂度 O(N)
    // -- SPFA 时间复杂度某种情况下略高于 Dijkstra，适合稀疏图
    // -- SPFA 可以用于带有负权的图，而 Dijkstra 的贪心策略则不行
    // -- SPFA 可以判断图是否存在负环，用一个 cnt[x] 数组来存放经过这个点的次数，最坏情况下每个节点入队 V−1 次，如果 cnt[x] = V，那就说明存在负环了
    public int networkDelayTimeHeapSPFA1(int[][] times, int n, int k) {
        // 构图 - 邻接表
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] e : times) {
            int x = e[0], y = e[1], d = e[2];
            graph.computeIfAbsent(x, t -> new ArrayList<>()).add(new int[]{y, d});
        }

        // SPFA
        int[] dist = spfa1(graph, n, k);

        int res = Arrays.stream(dist).max().getAsInt();
        return res == 0x3f3f3f3f ? -1 : res;
    }

    private int[] spfa1(Map<Integer, List<int[]>> graph, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0x3f3f3f3f);
        // base case
        dist[k] = dist[0] = 0;

        boolean[] vis = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            // 可以重复入队
            vis[poll] = false;

            List<int[]> list = graph.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];

                // 如果没更新过，或者需要更新距离
                if (dist[next] == 0x3f3f3f3f || dist[next] > dist[poll] + arr[1]) {
                    dist[next] = dist[poll] + arr[1];

                    // 如果队列中没有，就可以入队，不要重复入队
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        return dist;
    }

    // SPFA + 邻接矩阵 - 时间复杂度 O(E) - 空间复杂度 O(N)
    public int networkDelayTimeHeapSPFA2(int[][] times, int n, int k) {
        // 构图 - 邻接矩阵
        int[][] g = new int[n + 1][n + 1];
        // 初始化图
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g[i][j] = i == j ? 0 : -1;
            }
        }
        // 构图
        for (int[] e : times) {
            g[e[0]][e[1]] = e[2];
        }

        // SPFA
        int[] dist = spfa2(g, n, k);

        int res = Arrays.stream(dist).max().getAsInt();
        return res == 0x3f3f3f3f ? -1 : res;
    }

    private int[] spfa2(int[][] g, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0x3f3f3f3f);
        // base case
        dist[k] = dist[0] = 0;

        boolean[] vis = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        vis[k] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            vis[poll] = false;

            // 遍历邻居
            for (int next = 1; next <= n; next++) {
                int w = g[poll][next];
                // 如果是邻居 且 如果没有更新过，或者是需要更新，才往下走，注意这里是且，并不是判断了邻居就往下走了
                if (w != -1 && (dist[next] == 0x3f3f3f3f || dist[next] > dist[poll] + w)) {
                    dist[next] = dist[poll] + w;

                    // 如果队列中没有，就可以入队，不要重复入队
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        return dist;
    }

}
