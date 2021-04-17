package algorithm.leetcode.双周赛.lc_2021_4_17_滴滴;

import java.util.Arrays;

/**
 * 每个查询的最大异或值
 * <p>题目链接: https://leetcode-cn.com/problems/maximum-xor-for-each-query/</p>
 * <p>题解链接: https://leetcode-cn.com/problems/maximum-xor-for-each-query/solution/java-wei-yun-suan-by-echofzoe-pmls/</p>
 *
 * @author echofzoe
 * @since 2021.4.17
 */
public class Lc_5719_每个查询的最大异或值 {

    public static void main(String[] args) {
        Lc_5719_每个查询的最大异或值 lc = new Lc_5719_每个查询的最大异或值();

        int[] nums = {0, 1, 1, 3};
        int maximumBit = 2;

        System.out.println("在条件 nums = " + Arrays.toString(nums) + "和 maximumBit = " + maximumBit + "下，能得到的每个查询的最大异或值是" + Arrays.toString(lc.getMaximumXor(nums, maximumBit)));
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;

        int[] res = new int[n];

        int x = 666, maxK = Integer.MAX_VALUE >> (31 - maximumBit);

        // 一些结论：
        // - <1> 0 ^ x = x
        // - <2> 设 m 为大于 x 的最小的 2 的次幂, 则 ~x & (m - 1) 中的每一位二进制位都与 x & (m - 1) 中的相反

        // 算法：
        // - 直接遍历源数组, 遍历中保留前缀异或状态, 记为 x
        // - x  = 0 时, 本次查询的最大值由 0 ^ maxK 得到, 故本次查询的 k = maxK
        // - x != 0 时, 本次查询的最大值由 x ^ (~x & maxK) 得到, 故本次查询的 k = ~x & maxK
        for (int i = 0; i < n; i++) {
            if (i == 0) x = nums[0];
            else x ^= nums[i];

            res[n - 1 - i] = x == 0 ? maxK : ~x & maxK;
        }

        return res;
    }

}
