package algorithm.leetcode.周赛.lc_2021_1_31_图灵教育;

import java.util.*;

public class Lc_1743_从相邻元素对还原数组 {

    // 从相邻元素对还原数组
    // https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/

    public static void main(String[] args) {
        Lc_1743_从相邻元素对还原数组 lc = new Lc_1743_从相邻元素对还原数组();
        int[][] adjacentPairs = {{4, -2}, {1, 4}, {-3, 1}};

        System.out.println("从相邻元素对数组" + Arrays.deepToString(adjacentPairs) + "还原数组的结果是" + Arrays.toString(lc.restoreArray(adjacentPairs)));
    }

    // hash - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] a : adjacentPairs) {
            map.computeIfAbsent(a[0], key -> new ArrayList<>()).add(a[1]);
            map.computeIfAbsent(a[1], key -> new ArrayList<>()).add(a[0]);
        }

        int[] res = new int[n + 1];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                res[0] = entry.getKey();
                res[1] = entry.getValue().get(0);

                for (int i = 2; i < n + 1; i++) {
                    List<Integer> next = map.get(res[i - 1]);

                    res[i] = next.get(0) == res[i - 2] ? next.get(1) : next.get(0);
                }

                break;
            }
        }

        return res;
    }

}
