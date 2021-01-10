package algorithm.leetcode.周赛.lc_2021_1_10_图灵教育;

import java.util.*;

public class Lc_5650_执行交换操作后的最小汉明距离 {

    // 执行交换操作后的最小汉明距离
    // https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations/

    public static void main(String[] args) {
        Lc_5650_执行交换操作后的最小汉明距离 lc = new Lc_5650_执行交换操作后的最小汉明距离();
        int[] source = {5, 1, 2, 4, 3};
        int[] target = {1, 5, 4, 2, 3};
        int[][] allowedSwaps = {{0, 4}, {4, 2}, {1, 3}, {1, 4}};

        System.out.println("数组" + Arrays.toString(source) + "经过任意次" + Arrays.deepToString(allowedSwaps) + "中的操作后，与" + Arrays.toString(target) + "之间的最小汉明距离是" + lc.minimumHammingDistance(source, target, allowedSwaps));
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int res = 0;

        UnionFindSet ufs = new UnionFindSet(n);

        for (int[] arr : allowedSwaps) {
            ufs.union(arr[0], arr[1]);
        }

        // <当前元素, 当前元素索引的集合>
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(target[i], new LinkedList<>());
            list.add(i);
            map.put(target[i], list);
        }

        for (int i = 0; i < n; i++) {
            int cur = source[i];

            if (!map.containsKey(cur)) {
                res++;
            } else {
                // source 的数字 cur 在 target 中可以找到
                // 默认不在一个联通器内
                boolean flag = false;
                List<Integer> list = map.get(cur);

                // 遍历 cur 在 target 中的索引
                for (int j = 0; j < list.size(); j++) {
                    int curIndex = list.get(j);

                    // source[i] 可以通过若干操作移动到与之相等的 source[curIndex]
                    if (ufs.isConnected(i, curIndex)) {
                        list.remove(j);
                        map.put(cur, list);
                        flag = true;

                        break;
                    }
                }

                // cur 在 target 中的所有索引都不与 i 联通
                if (!flag) {
                    res++;
                }
            }
        }

        return res;
    }

}

class UnionFindSet {
    private int[] parent;
    private int n;

    public UnionFindSet(int n) {
        this.n = n;
        this.parent = new int[n];

        // 初始化 - 自己和自己联通
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 找到x的根节点
    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        // 路径压缩
        parent[x] = find(parent[x]);
        return parent[x];
    }

    // 联通
    public void union(int x, int y) {
        int index1 = find(x), index2 = find(y);

        if (index1 == index2) return;

        parent[index1] = index2;
    }

    // 判断是否联通
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

}
