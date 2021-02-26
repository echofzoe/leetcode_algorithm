package algorithm.leetcode.matrix;

import java.util.*;

public class Lc_1178_猜字谜 {

    // 猜字谜
    // https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/

    public static void main(String[] args) {
        Lc_1178_猜字谜 lc = new Lc_1178_猜字谜();
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        System.out.println("谜面数组" + Arrays.toString(puzzles) + "在谜底数组" + Arrays.toString(words) + "中能找到的结果是" + lc.findNumOfValidWords(words, puzzles));
    }

    // 二进制状态压缩 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int n = words.length, m = puzzles.length;

        List<Integer> res = new ArrayList<>();

        Map<Integer, Integer> state = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String cur = words[i];
            int tmp = 0;
            for (int j = 0; j < cur.length(); j++) {
                tmp = tmp | (1 << cur.charAt(j) - 'a');
            }
            state.put(tmp, state.getOrDefault(tmp, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            String cur = puzzles[i];
            int tmp = 0;
            for (int j = 0; j < cur.length(); j++) {
                tmp = tmp | (1 << cur.charAt(j) - 'a');
            }

            int cnt = 0;
            // 枚举子集
            for (int j = tmp; j != 0; j = (j - 1) & tmp) {
                // 还需要满足条件 1
                // 下面的 if 语句的运算符优先级从大到小为 减法 - 位移 - 位与
                if ((1 << cur.charAt(0) - 'a' & j) != 0) {
                    cnt += state.getOrDefault(j, 0);
                }
            }

            res.add(cnt);
        }

        return res;
    }

}
