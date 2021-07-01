package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 公交路线
 * <P>https://leetcode-cn.com/problems/bus-routes/</P>
 *
 * @author echofzoe
 * @since 2021.7.1
 */
public class Lc_815_公交路线 {

    public static void main(String[] args) {
        Lc_815_公交路线 lc = new Lc_815_公交路线();

        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1, target = 6;

        System.out.println("给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。\n" +
                "  - 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。\n" +
                "现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。\n" +
                "求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。\n");
        System.out.println("输入：routes = " + Arrays.deepToString(routes) + ", source = " + source + ", target = " + target);
        System.out.println("输出：" + lc.numBusesToDestination(routes, source, target));  // 2
    }

    // bfs - 时间复杂度 O(nm+n^2) 其中n是公交路线的数量，m是车站的总数量 - 空间复杂度 O(n^2+m)
    public int numBusesToDestination(int[][] r, int s, int t) {
        if (s == t) return 0;
        int n = r.length;

        // 记录经过的路线, 0 <= 参数 < n
        Deque<Integer> d = new LinkedList<>();
        // 记录进入某路线所需的距离
        Map<Integer, Integer> dist = new HashMap<>();
        // 记录某个车站可以进入的路线
        Map<Integer, Set<Integer>> next = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int station : r[i]) {
                if (station == s) {
                    // 将起点所属的路线加入队列
                    d.add(i);
                    dist.put(i, 1);  // 加入进入当前路线所需距离
                }

                // 记录当前车站所能达到的路线 i
                Set<Integer> set = next.getOrDefault(station, new HashSet<>());
                set.add(i);
                next.put(station, set);
            }
        }

        while (!d.isEmpty()) {
            int cur = d.poll();
            int step = dist.get(cur);

            for (int sta : r[cur]) {
                if (sta == t) return step;

                // 将由当前路线cur发起的新路线加入队列
                Set<Integer> lines = next.get(sta);
                if (lines == null) continue;
                for (int l : lines) {
                    if (!dist.containsKey(l)) {
                        dist.put(l, step + 1);
                        d.add(l);
                    }
                }
            }
        }

        return -1;
    }

}
