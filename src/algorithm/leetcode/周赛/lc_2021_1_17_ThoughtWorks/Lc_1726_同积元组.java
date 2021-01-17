package algorithm.leetcode.周赛.lc_2021_1_17_ThoughtWorks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_1726_同积元组 {

    // 同积元组
    // https://leetcode-cn.com/problems/tuple-with-same-product/

    public static void main(String[] args) {
        Lc_1726_同积元组 lc = new Lc_1726_同积元组();
        int[] nums = {2, 3, 4, 6, 8, 12};

        System.out.println("不含相同元素的数组" + Arrays.toString(nums) + "中同积元组的数量是" + lc.tupleSameProductHash (nums));
    }

    // 四指针 - 时间复杂度 O(N^3) - 空间复杂度 O(1)
    public int tupleSameProductFourPointer(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int a = 0; a < n - 3; a++) {
            for (int d = n - 1; d >= a + 3; d--) {
                int b = a + 1, c = d - 1;

                while (b < c) {
                    int ad = nums[a] * nums[d], bc = nums[b] * nums[c];

                    if (ad == bc) {
                        res += 8;
                        b++;
                        c--;
                    } else if (ad > bc) {
                        b++;
                    } else {
                        c--;
                    }
                }
            }
        }

        return res;
    }

    // hash - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int tupleSameProductHash(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int val = nums[i] * nums[j];
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
        }

        int res = 0;
        for (int val : map.values()) {
            if (val >= 2) {
                // 排列组合 - C(val, 2) = val! / 2!
                res += val * (val - 1) / 2;
            }
        }

        return res * 8;
    }

}
