package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_1579_保证图可完全遍历 {

    // 保证图可完全遍历
    // https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/

    public static void main(String[] args) {
        Lc_1579_保证图可完全遍历 lc = new Lc_1579_保证图可完全遍历();
        int n = 4;
        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};

        System.out.println(Arrays.deepToString(edges) + "在保证图可以完全遍历的情况下可以删除的最大边数是 " + lc.maxNumEdgesToRemove(n, edges));
    }

    // 并查集 - 时间复杂度 O() - 空间复杂度 O()
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFindSet u1 = new UnionFindSet(n);
        UnionFindSet u2 = new UnionFindSet(n);

        // 将节点索引改为从0开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        int res = 0;

        // 处理公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!u1.isConnected(edge[1], edge[2])) {
                    u1.union(edge[1], edge[2]);
                    u2.union(edge[1], edge[2]);
                } else {
                    res++;
                }
            }
        }

        // 处理独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!u1.isConnected(edge[1], edge[2])) {
                    u1.union(edge[1], edge[2]);
                } else {
                    res++;
                }
            }

            if (edge[0] == 2) {
                if (!u2.isConnected(edge[1], edge[2])) {
                    u2.union(edge[1], edge[2]);
                } else {
                    res++;
                }
            }
        }

        if (u1.getSetCount() != 1 || u2.getSetCount() != 1) return -1;
        return res;
    }

}
