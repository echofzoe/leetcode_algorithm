package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 传递信息
 * <P>https://leetcode-cn.com/problems/chuan-di-xin-xi/</P>
 *
 * @author echofzoe
 * @since 2021.7.1
 */
public class Lcp_07_传递信息 {

    public static void main(String[] args) {
        Lcp_07_传递信息 lc = new Lcp_07_传递信息();

        int n = 5, k = 3;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};

        System.out.println("小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：\n" +
                "  - 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0\n" +
                "  - 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。\n" +
                "  - 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人\n" +
                "给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。\n");
        System.out.println("输入：n = " + n + ", relation = " + Arrays.deepToString(relation) + ", k = " + k);
        System.out.println("输出：" + lc.numWays(n, relation, k));  // 3
    }

    // bfs
    // - 时间复杂度 O(n^k) 最多需要遍历k层，每层最多有n-1个分支
    // - 空间复杂度 O(n+m+n^k) 其中m为relation数组长度，保存有向图所需空间O(n+m)，遍历到第k层时，队列的大小为O(n^k)
    public int numWays(int n, int[][] relation, int k) {
        // 建图
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] edge : relation) {
            edges.get(edge[0]).add(edge[1]);
        }

        Deque<Integer> d = new LinkedList<>() {{
            add(0);
        }};

        int step = 0;
        while (!d.isEmpty() && step < k) {
            int size = d.size();
            step++;

            for (int i = 0; i < size; i++) {
                int cur = d.poll();
                List<Integer> t = edges.get(cur);
                d.addAll(t);
            }
        }

        int res = 0;
        if (step == k) {
            while (!d.isEmpty()) {
                if (d.poll() == n - 1) {
                    res++;
                }
            }
        }

        return res;
    }

    // dfs
    // - 时间复杂度 O()
    // - 空间复杂度 O()
    int n, k, res;
    List<List<Integer>> edges;

    public int numWays1(int n, int[][] relation, int k) {
        this.n = n;
        this.k = k;
        res = 0;

        // 建图
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] edge : relation) {
            edges.get(edge[0]).add(edge[1]);
        }

        dfs(0, 0);

        return res;
    }

    private void dfs(int idx, int steps) {
        // exit
        if (steps == k) {
            if (idx == n - 1) {
                res++;
            }
            return;
        }

        // dfs
        List<Integer> t = edges.get(idx);
        for (int next : t) {
            dfs(next, steps + 1);
        }
    }

}
