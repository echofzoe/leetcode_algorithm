package algorithm.leetcode.bit;

import java.util.Arrays;

/**
 * 汉明距离总和
 * <P>https://leetcode-cn.com/problems/total-hamming-distance/</P>
 *
 * @author echofzoe
 * @since 2021.5.28
 */
public class Lc_477_汉明距离总和 {

    public static void main(String[] args) {
        Lc_477_汉明距离总和 lc = new Lc_477_汉明距离总和();

        int[] nums = {4, 14, 2};

        System.out.println("两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。\n" +
                "计算一个数组中，任意两个数之间汉明距离的总和。\n" +
                "注意:\n" +
                "  - 数组中元素的范围为从 0 到 10^9。\n" +
                "  - 数组的长度不超过 10^4。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.totalHammingDistance(nums));  // 6
    }

    // 逐位统计 - 时间复杂度 O(NL) N为数组长度，L为30，由于10^9<2^30，我们可以直接从二进制的第0位枚举到第29位 - 空间复杂度 O(1)
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;

        int total = 0;
        for (int i = 0; i < 30; i++) {
            int a = 0;
            for (int x : nums) {
                a += (x >> i) & 1;
            }
            total += a * (n - a);
        }

        return total;
    }

}
