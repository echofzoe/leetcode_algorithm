package algorithm.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 大餐计数
 * <P>https://leetcode-cn.com/problems/count-good-meals/</P>
 *
 * @author echofzoe
 * @since 2021.7.7
 */
public class Lc_1711_大餐计数 {

    public static void main(String[] args) {
        Lc_1711_大餐计数 lc = new Lc_1711_大餐计数();

        int[] deliciousness = {1, 1, 1, 3, 3, 3, 7};

        System.out.println("大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。\n" +
                "你可以搭配 任意 两道餐品做一顿大餐。\n" +
                "给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。\n" +
                "注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。\n");
        System.out.println("输入：" + Arrays.toString(deliciousness));
        System.out.println("输出：" + lc.countPairs(deliciousness));  // 15
    }

    // hash
    // - 时间复杂度 O(N*logC) C是数组deliciousness中的元素值上限，这道题中C=2^20。需要遍历数组deliciousness一次，对于其中的每个元素，需要O(logC)的时间计算包含该元素的大餐数量
    // - 空间复杂度 O(N)
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;

        int maxVal = 0;
        for (int x : deliciousness) maxVal = Math.max(x, maxVal);
        int maxSum = maxVal * 2;

        Map<Integer, Integer> m = new HashMap<>();

        long res = 0;
        for (int cur : deliciousness) {
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int cnt = m.getOrDefault(sum - cur, 0);
                res = (res + cnt) % MOD;
            }
            m.put(cur, m.getOrDefault(cur, 0) + 1);
        }

        return (int) res;
    }

}
