package algorithm.leetcode.周赛.lc_2021_1_3_深信服;

import java.io.PipedWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_5642_大餐计数 {

    // 大餐计数
    // https://leetcode-cn.com/problems/count-good-meals/

    public static void main(String[] args) {
        Lc_5642_大餐计数 lc = new Lc_5642_大餐计数();
        int[] deliciousness = {1, 1, 1, 3, 3, 3, 7};

        System.out.println("在餐品美味程度为" + Arrays.toString(deliciousness) + "的情况下，可以组合出" + lc.countPairs(deliciousness) + "道大餐");
    }

    public int countPairs(int[] deliciousness) {
        Arrays.sort(deliciousness);

        Map<Integer, Integer> map = new HashMap<>();
        // pow 取值区间为 [1, 2, 4, 8, ..., 2^31]
        int res = 0, pow = 1;

        for (int i = 0; i < 32; i++) {
            for (int k : deliciousness) {
                Integer cur = map.get(k);
                if (cur != null) res = (res + cur) % 1000000007;

                Integer need = map.get(pow - k);

                map.put(pow - k, need == null ? 1 : need + 1);
            }

            map.clear();
            pow <<= 1;
        }

        return res;
    }

}
