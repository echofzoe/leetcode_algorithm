package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 等差数列划分 II - 子序列
 * <P>https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.8.11
 */
public class Lc_446_等差数列划分_II_子序列 {

    public static void main(String[] args) {
        Lc_446_等差数列划分_II_子序列 lc = new Lc_446_等差数列划分_II_子序列();

        int[] nums = {2, 4, 6, 8, 10};

        System.out.println("给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。\n" +
                "如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。\n" +
                "  - 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。\n" +
                "  - 再例如，[1, 1, 2, 5, 7] 不是等差序列。\n" +
                "数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。\n" +
                "  - 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。\n" +
                "题目数据保证答案是一个 32-bit 整数。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.numberOfArithmeticSlices(nums));
    }

    // dp - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        Map<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }


        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 计算差值
                long d = 1L * nums[i] - nums[j];

                // 获得以nums[j]为结尾，等差为d的子序列的个数
                int cnt = dp[j].getOrDefault(d, 0);

                // 更新答案
                res += cnt;

                // 更新以nums[i]为结尾，等差为d的子序列的个数
                dp[i].put(d, dp[i].getOrDefault(d, 0) + cnt + 1);
            }
        }

        return res;
    }

}
