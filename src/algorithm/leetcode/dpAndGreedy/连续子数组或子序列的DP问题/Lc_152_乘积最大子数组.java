package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 乘积最大子数组
 * <P>https://leetcode-cn.com/problems/maximum-product-subarray/</P>
 *
 * @author echofzoe
 * @since 2021.4.15
 */
public class Lc_152_乘积最大子数组 {

    public static void main(String[] args) {
        Lc_152_乘积最大子数组 lc = new Lc_152_乘积最大子数组();

        int[] nums1 = {-2};
        int[] nums2 = {-2, 0, -1};
        int[] nums3 = {2, 3, -2, 4};

        System.out.println("给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。");
        System.out.println("- 输入：" + Arrays.toString(nums1) + "，输出：" + lc.maxProduct(nums1));
        System.out.println("- 输入：" + Arrays.toString(nums2) + "，输出：" + lc.maxProduct(nums2));
        System.out.println("- 输入：" + Arrays.toString(nums3) + "，输出：" + lc.maxProduct(nums3));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        // dp[i][0] 表示以元素nums[i]结尾的连续子数组的最小值, dp[i][1] 表示以元素nums[i]结尾的连续子数组的最大值
        int[][] dp = new int[n][2];
        // base case
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];

            if (cur < 0) {
                dp[i][0] = Math.min(dp[i - 1][1] * cur, cur);
                dp[i][1] = Math.max(dp[i - 1][0] * cur, cur);
            } else {
                dp[i][0] = Math.min(dp[i - 1][0] * cur, cur);
                dp[i][1] = Math.max(dp[i - 1][1] * cur, cur);
            }
        }

        int res = Integer.MIN_VALUE;
        for (int[] d : dp) res = Math.max(res, d[1]);

        return res;
    }

}
