package algorithm.leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_1631_最小体力消耗路径 {

    // 最小体力消耗路径
    // https://leetcode-cn.com/problems/path-with-minimum-effort/

    public static void main(String[] args) {
        Lc_1631_最小体力消耗路径 lc = new Lc_1631_最小体力消耗路径();
        // 1   2   2
        // 3   8   3
        // 5   3   5
        int[][] heights = {{1, 2, 2}, {3, 8, 3}, {5, 3, 5}};

        System.out.println("在二维地图" + Arrays.deepToString(heights) + "中从左上角到右下角的最小体力消耗值为 " + lc.minimumEffortPathBfs(heights));
    }

    // 并查集 - 时间复杂度 O(MNlog(MN)) - 空间复杂度 O(MN)
    public int minimumEffortPathUfs(int[][] heights) {

        int res = 0;

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
