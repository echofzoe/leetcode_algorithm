package algorithm.leetcode.bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_260_只出现一次的数字III {

    // 只出现一次的数字 III
    // https://leetcode-cn.com/problems/single-number-iii/

    public static void main(String[] args) {
        Lc_260_只出现一次的数字III lc = new Lc_260_只出现一次的数字III();
        int[] input = {1, 2, 1, 3, 2, 5};

        System.out.println("数组" + Arrays.toString(input) + "中只出现一次的数字有" + Arrays.toString(lc.singleNumberBitmask(input)));
    }

    // 位运算 + 掩码 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] singleNumberBitmask(int[] nums) {
        int bitmask = 0;

        for (int num : nums) {
            bitmask ^= num;
        }

        // 保留位中最右边的1，将其余的1设为0
        // - 在 x & (-x) 中，-x = ~x + 1，即取反加一
        // - 故 x & (-x) = x & (~x + 1)
        int diff = bitmask & (-bitmask);

        int x = 0;
        for (int num : nums) {
            if ((num & diff) != 0) {
                x ^= num;
            }
        }

        return new int[]{x, bitmask ^ x};
    }
    
    // 哈希表 - 时间复杂度 O() - 空间复杂度 O()
    public int[] singleNumberHash(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() == 1) res[index++] = item.getKey();
        }

        return res;
    }

}
