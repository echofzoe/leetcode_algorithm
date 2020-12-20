package algorithm.leetcode.周赛.lc_2020_12_20_开课吧;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_5630_删除子数组的最大得分 {

    // 删除子数组的最大得分
    // https://leetcode-cn.com/problems/maximum-erasure-value/

    public static void main(String[] args) {
        Lc_5630_删除子数组的最大得分 lc = new Lc_5630_删除子数组的最大得分();
        int[] input1 = {4, 2, 4, 5, 6};
        int[] input2 = {5, 2, 1, 2, 5, 2, 1, 2, 5};

        System.out.println("数组" + Arrays.toString(input1) + "中的连续子序列的最大和是" + lc.maximumUniqueSubarray(input1));
        System.out.println("数组" + Arrays.toString(input2) + "中的连续子序列的最大和是" + lc.maximumUniqueSubarray(input2));
    }

    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length, res = 0;

        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // 记录上一次出现的重复字符的索引
        Map<Integer, Integer> map = new HashMap<>();

        // 子序列的开始索引
        int start = 0;

        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            if (map.containsKey(cur)) {
                start = Math.max(start, map.get(cur) + 1);
            }

            res = Math.max(res, prefixSum[i + 1] - prefixSum[start]);

            map.put(cur, i);
        }

        return res;
    }

}
