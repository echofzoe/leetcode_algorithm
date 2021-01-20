package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.*;

public class Lc_1584_连接所有点的最小费用 {

    // 连接所有点的最小费用
    // https://leetcode-cn.com/problems/min-cost-to-connect-all-points/

    public static void main(String[] args) {
        Lc_1584_连接所有点的最小费用 lc = new Lc_1584_连接所有点的最小费用();
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};

        System.out.println("连接" + Arrays.deepToString(points) + "中所有点的最小费用是" + lc.minCostConnectPoints(points));
    }

    public int minCostConnectPoints(int[][] points) {
        int m = points.length;
        if (m == 0) return 0;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                edges.add(new Edge(i, j, dist(i, j, points)));
            }
        }

        edges.sort(Comparator.comparingInt(e -> e.len));

        int res = 0, count = 1;
        UnionFindSet ufs = new UnionFindSet(m);
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;

            if (!ufs.isConnected(x, y)) {
                ufs.union(x, y);
                res += len;
                count++;
            }
        }

        return res;
    }

    private int dist(int x, int y, int[][] points) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

}

class Edge {
    int x, y, len;

    public Edge(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }
}