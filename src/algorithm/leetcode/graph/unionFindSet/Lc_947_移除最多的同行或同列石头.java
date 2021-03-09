package algorithm.leetcode.graph.unionFindSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_947_移除最多的同行或同列石头 {

    // 移除最多的同行或同列石头
    // https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/

    public static void main(String[] args) {
        Lc_947_移除最多的同行或同列石头 lc = new Lc_947_移除最多的同行或同列石头();
        int[][] stones = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};

        System.out.println("在二维平面的整数坐标点" + Arrays.deepToString(stones) + "上分布着石头，如果一块石头的同行或者同列上有其他石头存在，那么就可以移除这块石头，则需要移除" + lc.removeStones(stones) + "块石头");
    }

    // DFS - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int removeStones(int[][] stones) {
        int n = stones.length;
        if (n == 0) return 0;

        // 1, 0, 1
        // 0, 1, 0
        // 1, 0, 1
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edge.get(i).add(j);
                }
            }
        }

        boolean[] visited = new boolean[n];
        int union = 0;    // 连通块的数量
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                union++;
                dfs(i, edge, visited);
            }
        }

        return n - union;
    }

    private void dfs(int i, List<List<Integer>> edge, boolean[] visited) {
        visited[i] = true;

        for (int j : edge.get(i)) {
            if (!visited[j]) {
                dfs(j, edge, visited);
            }
        }
    }

}
