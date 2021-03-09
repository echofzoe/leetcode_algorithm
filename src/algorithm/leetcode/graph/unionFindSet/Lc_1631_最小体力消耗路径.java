package algorithm.leetcode.graph.unionFindSet;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.*;

public class Lc_1631_最小体力消耗路径 {

    // 最小体力消耗路径
    // https://leetcode-cn.com/problems/path-with-minimum-effort/

    public static void main(String[] args) {
        Lc_1631_最小体力消耗路径 lc = new Lc_1631_最小体力消耗路径();
        // 1   2   2
        // 3   8   3
        // 5   3   5
        int[][] heights = {{1, 2, 2}, {3, 8, 3}, {5, 3, 5}};

        System.out.println("在二维地图" + Arrays.deepToString(heights) + "中从左上角到右下角的最小体力消耗值为 " + lc.minimumEffortPathUfs(heights));
    }

    // 并查集 - 时间复杂度 O(MNlog(MN)) - 空间复杂度 O(MN)
    public int minimumEffortPathUfs(int[][] heights) {
        int m = heights.length, n = heights[0].length;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (i > 0) edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                if (j > 0) edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
            }
        }

        edges.sort(Comparator.comparingInt(x -> x[2]));

        UnionFindSet ufs = new UnionFindSet(m * n);
        int res = 0, end = m * n - 1;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], d = edge[2];
            ufs.union(x, y);
            if (ufs.isConnected(0, end)) {
                res = d;
                break;
            }
        }

        return res;
    }

    // Dijkstra - 时间复杂度 O(MNlog(MN)) - 空间复杂度 O(MN)
    public int minimumEffortPathBfs(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // 上、下、左、右
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        pq.offer(new int[]{0, 0, 0});

        boolean[] visited = new boolean[m * n];
        int[] dists = new int[m * n];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[0] = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];

            int id = x * n + y;
            if (visited[id]) continue;

            if (x == m - 1 && y == n - 1) break;

            visited[id] = true;

            for (int i = 0; i < 4; i++) {
                int x1 = x + dirs[i][0], y1 = y + dirs[i][1], id1 = x1 * n + y1;
                if (x1 < 0 || x1 > m - 1 || y1 < 0 || y1 > n - 1 || Math.max(d, Math.abs(heights[x][y] - heights[x1][y1])) >= dists[id1])
                    continue;

                dists[id1] = Math.max(d, Math.abs(heights[x][y] - heights[x1][y1]));
                pq.offer(new int[]{x1, y1, dists[id1]});
            }
        }

        return dists[m * n - 1];
    }

}
