package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_684_冗余连接 {

    // 冗余连接
    // https://leetcode-cn.com/problems/redundant-connection/

    public static void main(String[] args) {
        Lc_684_冗余连接 lc = new Lc_684_冗余连接();
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};

        System.out.println("删去无向图" + Arrays.deepToString(edges) + "中的边" + Arrays.toString(lc.findRedundantConnection(edges)) + "后，可以得到一棵树");
    }

    // 并查集 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        UnionFindSet ufs = new UnionFindSet(n + 1);
        for (int[] edge : edges) {

            if (ufs.isConnected(edge[0], edge[1])) {
                return edge;
            }

            ufs.union(edge[0], edge[1]);
        }

        return new int[0];
    }

}
