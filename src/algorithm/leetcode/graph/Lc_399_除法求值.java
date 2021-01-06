package algorithm.leetcode.graph;

import java.util.*;

public class Lc_399_除法求值 {

    // 除法求值
    // https://leetcode-cn.com/problems/evaluate-division/

    public static void main(String[] args) {
        Lc_399_除法求值 lc = new Lc_399_除法求值();
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>(Arrays.asList("a", "b")));
        equations.add(new ArrayList<>(Arrays.asList("b", "c")));
        equations.add(new ArrayList<>(Arrays.asList("bc", "cd")));
        double[] values = new double[]{1.5, 2.5, 5.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(Arrays.asList("a", "c")));
        queries.add(new ArrayList<>(Arrays.asList("c", "b")));
        queries.add(new ArrayList<>(Arrays.asList("bc", "cd")));
        queries.add(new ArrayList<>(Arrays.asList("cd", "bc")));
        queries.add(new ArrayList<>(Arrays.asList("x", "y")));

        System.out.println("输出为" + Arrays.toString(lc.calcEquation(equations, values, queries)));
    }

    double[] res;
    boolean pathCanReach;

    // dfs - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        int n1 = queries.size();

        this.res = new double[n1];

        Map<String, List<Pair>> g = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();

        // 构建无向图
        // String - double - String, eg: a - 3 - b or b - 1/3 - a
        for (int i = 0; i < n; i++) {
            String key = equations.get(i).get(0);
            String target = equations.get(i).get(1);
            Pair k2tPair = new Pair(target, values[i]);
            Pair t2kPair = new Pair(key, 1 / values[i]);

            List<Pair> tmp;
            if (!g.containsKey(key)) {
                tmp = new ArrayList<>();
                tmp.add(k2tPair);
                g.put(key, tmp);
            } else if (g.containsKey(key) && !isContainsPair(g.get(key), target)) {
                tmp = g.get(key);
                tmp.add(k2tPair);
                g.put(key, tmp);
            }

            if (!g.containsKey(target)) {
                tmp = new ArrayList<>();
                tmp.add(t2kPair);
                g.put(target, tmp);
            } else if (g.containsKey(target) && !isContainsPair(g.get(target), key)) {
                tmp = g.get(target);
                tmp.add(t2kPair);
                g.put(target, tmp);
            }
        }

        // 遍历 queries, 对每一组进行 dfs 计算结果
        // - queries[i] 在图中相连接, 则路径上的乘积就是结果
        // - queries[i] 在图中不连接, 则返回 -1.0
        for (int i = 0; i < n1; i++) {
            String key = queries.get(i).get(0);
            String target = queries.get(i).get(1);

            if (!g.containsKey(key)) {
                res[i] = -1.0;
                continue;
            }

            pathCanReach = false;
            double path = 1.0;

            visited.put(key, true);
            dfs(g, visited, key, target, i, path);
            visited.put(key, false);

            if (!pathCanReach) res[i] = -1.0;
        }

        return res;
    }

    private void dfs(Map<String, List<Pair>> g, Map<String, Boolean> visited, String key, String target, int index, double path) {
        if (pathCanReach) return;

        if (key.equals(target)) {
            pathCanReach = true;
            res[index] = path;
            return;
        }

        List<Pair> candidate = g.get(key);    // 当前的 key 所连接的所有通路
        for (Pair pair : candidate) {
            String cur = pair.s;
            if (!visited.containsKey(cur) || (visited.containsKey(cur) && !visited.get(cur))) {
                visited.put(cur, true);
                dfs(g, visited, pair.s, target, index, path * pair.d);
                visited.put(cur, false);
            }
        }
    }

    // 判断 key 所连接的通路上是否包含当前的 target
    private boolean isContainsPair(List<Pair> pairs, String target) {
        for (Pair cur : pairs) {
            if (cur.s.equals(target)) return true;
        }

        return false;
    }

    static class Pair {
        String s;    // target string
        double d;    // result of current division in path

        Pair(String s, double d) {
            this.s = s;
            this.d = d;
        }
    }

}
