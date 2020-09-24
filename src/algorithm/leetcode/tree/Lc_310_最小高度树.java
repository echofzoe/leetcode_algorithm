package algorithm.leetcode.tree;

import java.util.*;

public class Lc_310_最小高度树 {

    // 最小高度树
    // - 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点
    // https://leetcode-cn.com/problems/minimum-height-trees/

    public static void main(String[] args) {
        Lc_310_最小高度树 lc = new Lc_310_最小高度树();
        int n = 6;
        int[][] edges = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};

        System.out.println("无向图 " + Arrays.deepToString(edges) + " 中最小高度树的根节点有 " + Arrays.toString(lc.findMinHeightTrees(n, edges).toArray()));
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();

        if (n == 1) {
            res.add(0);
            return res;
        }

        // 1.建立各个节点的出度表
        int[] degree = new int[n];

        // 2.建立图关系，在每个节点的 list 中存储相连节点
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // 出度
            degree[edge[0]]++;
            degree[edge[1]]++;
            // 添加相邻节点
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // 3.将所有出度为 1 的节点，也就是叶子节点，入队，进行 bfs
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);

                // bfs
                List<Integer> neighbors = map.get(cur);
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return res;
    }
}
