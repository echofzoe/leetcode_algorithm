package algorithm.leetcode.array.prefixAndSuffix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_560_和为K的子数组 {

    // 和为K的子数组
    // https://leetcode-cn.com/problems/subarray-sum-equals-k/

    public static void main(String[] args) {
        Lc_560_和为K的子数组 lc = new Lc_560_和为K的子数组();
        int[] nums = {1, 1, 3, 4, 2, 2, 2, 1, 1, 2};
        int k = 8;

        System.out.println("数组" + Arrays.toString(nums) + "中和为" + k + "的连续的子数组的个数为" + lc.subarraySumPrefixOptimization(nums, k));
    }

    // 前缀和 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int subarraySumPrefix(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        // base case
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
//                if (prefixSum[i] - prefixSum[j] == k) {
//                    res++;
//                }
                if (prefixSum[j] == prefixSum[i] - k) {
                    res++;
                }
            }
        }

        return res;
    }

    // 前缀和优化 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int subarraySumPrefixOptimization(int[] nums, int k) {
        int n = nums.length;
        // map: k - 前缀和, v - 前缀和出现的次数
        Map<Integer, Integer> prefixSum = new HashMap<>();
        // base case
        prefixSum.put(0, 1);

        int res = 0, curPrefixSum = 0;
        for (int i = 0; i < n; i++) {
            // 当前索引下的前缀和
            curPrefixSum += nums[i];

            // 寻找目标前缀和
            int tmp = curPrefixSum - k;
            if (prefixSum.containsKey(tmp)) {
                res += prefixSum.get(tmp);
            }

            // 记录当前索引下的前缀和
            prefixSum.put(curPrefixSum, prefixSum.getOrDefault(curPrefixSum, 0) + 1);
        }

        return res;
    }

}
