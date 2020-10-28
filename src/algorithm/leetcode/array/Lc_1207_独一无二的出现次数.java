package algorithm.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lc_1207_独一无二的出现次数 {

    // 独一无二的出现次数
    // https://leetcode-cn.com/problems/unique-number-of-occurrences/

    public static void main(String[] args) {
        Lc_1207_独一无二的出现次数 lc = new Lc_1207_独一无二的出现次数();
        int[] arr = {1, 2, 2, 1, 1, 3};

        System.out.println("数组" + "中的每个数的出现次数" + (lc.uniqueOccurrences(arr) ? "都是" : "不是") + "独一无二的.");
    }

    // 两次哈希 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) return true;

        Map<Integer, Integer> occur = new HashMap<>();
        for (int i : arr) {
            if (occur.containsKey(i)) {
                occur.put(i, occur.get(i) + 1);
            } else {
                occur.put(i, 1);
            }
        }

        Set<Integer> freq = new HashSet<>();
        for (Map.Entry<Integer, Integer> m : occur.entrySet()) {
            freq.add(m.getValue());
        }

        return occur.size() == freq.size();
    }
}
