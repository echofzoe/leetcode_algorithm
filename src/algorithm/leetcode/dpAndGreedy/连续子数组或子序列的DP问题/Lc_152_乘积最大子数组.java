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

    // DP - 时间复杂度 O() - 空间复杂度 O()
    /* - dp[i][j] 表示以 nums[i] 结尾的连续子数组的最值，
       - 当 j = 0 时，表示计算的是最小值
       - 当 j = 1 时，表示计算的是最大值
       - 状态转移方程: {
         dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]), nums[i] >= 0
         dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]), nums[i] >= 0
         dp[i][0] = Math.max(dp[i - 1][1] * nums[i], nums[i]), nums[i] < 0
         dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]), nums[i] < 0
       }
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n][2];
        // base case
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
            } else {
                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
            }
        }

        int res = Integer.MIN_VALUE;
        for (int[] d : dp) res = Math.max(res, d[1]);

        return res;
    }

}
