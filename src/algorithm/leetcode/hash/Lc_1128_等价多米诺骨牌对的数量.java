package algorithm.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Lc_1128_等价多米诺骨牌对的数量 {

    // 等价多米诺骨牌对的数量
    // https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/

    public static void main(String[] args) {
        Lc_1128_等价多米诺骨牌对的数量 lc = new Lc_1128_等价多米诺骨牌对的数量();
        int[][] dominoes = {{1, 2}, {2, 1}, {3, 4}, {5, 6}};

        System.out.println(Arrays.deepToString(dominoes) + "中等价多米诺骨牌对的数量是 " + lc.numEquivDominoPairs(dominoes));
    }

    // hash - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Pair, Integer> freq = new HashMap<>();

        for (int[] one : dominoes) {
            Pair pair = new Pair(one[0], one[1]);
            freq.put(pair, freq.getOrDefault(pair, 0) + 1);
        }

        int res = 0;
        for (int val : freq.values()) res += val * (val - 1) / 2;

        return res;
    }

}

class Pair {
    private final int key;
    private final int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return key == pair.key && value == pair.value || key == pair.value && value == pair.key;
    }

    @Override
    public int hashCode() {
        if (key > value) return value * 10 + key;
        return key * 10 + value;
    }
}
