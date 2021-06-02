package algorithm.leetcode.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 连续的子数组和
 * <P>https://leetcode-cn.com/problems/continuous-subarray-sum/</P>
 *
 * @author echofzoe
 * @since 2021.6.2
 */
public class Lc_523_连续的子数组和 {

    public static void main(String[] args) {
        Lc_523_连续的子数组和 lc = new Lc_523_连续的子数组和();

        int[] nums = {23, 2, 6, 4, 7};
        int k = 6;

        System.out.println("给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：\n" +
                "  - 子数组大小 至少为 2 ，且\n" +
                "  - 子数组元素总和为 k 的倍数。\n" +
                "如果存在，返回 true ；否则，返回 false 。\n" +
                "注：如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("输出：" + lc.checkSubarraySum(nums, k));
    }

    // 前缀和 + hash
    // - 时间复杂度 O(N)
    // - 空间复杂度 O(min(N, k)) 其中N是数组nums的长度。空间复杂度主要取决于哈希表，哈希表中存储每个余数第一次出现的下标，最多有min(N,k)个余数
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + nums[i - 1];

        int remainder = 0;
        // 根据 同余定理，当 pre[q] − pre[p] 为 k 的倍数时，pre[p] 和 pre[q] 除以 k 的余数相同
        // 因此只需要计算每个下标对应的前缀和除以 k 的余数即可，使用哈希表存储每个余数第一次出现的下标
        Map<Integer, Integer> map = new HashMap<>();  // <余数, 下标>
        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            remainder = pre[i + 1] % k;

            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

}
