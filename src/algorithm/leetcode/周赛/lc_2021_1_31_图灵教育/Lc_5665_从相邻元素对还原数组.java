package algorithm.leetcode.周赛.lc_2021_1_31_图灵教育;


import com.sun.jdi.Value;

import java.security.Key;
import java.util.*;

public class Lc_5665_从相邻元素对还原数组 {

    // 从相邻元素对还原数组
    // https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/

    public static void main(String[] args) {
        Lc_5665_从相邻元素对还原数组 lc = new Lc_5665_从相邻元素对还原数组();
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
        map.forEach((key, value) -> {
            if (value.size() == 1 && res[0] == res[1]) {
                res[0] = key;
                res[1] = value.get(0);

                for (int i = 2; i < res.length; i++) {
                    int nextKey = map.get(res[i - 1]).get(0);

                    if (nextKey != res[i - 2]) res[i] = nextKey;
                    else res[i] = map.get(res[i - 1]).get(1);
                }
            }
        });

        return res;
    }

}
