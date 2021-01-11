package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

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
                boolean isUnion = false;
                List<Integer> list = map.get(cur);

                // 遍历 cur 在 target 中的索引
                for (int j = 0; j < list.size(); j++) {
                    int curIndex = list.get(j);

                    // source[i] 可以通过若干操作移动到与之相等的 source[curIndex]
                    if (ufs.isConnected(i, curIndex)) {
                        list.remove(j);
                        map.put(cur, list);
                        isUnion = true;

                        break;
                    }
                }

                // cur 在 target 中的所有索引都不与 i 联通
                if (!isUnion) {
                    res++;
                }
            }
        }

        return res;
    }

}
