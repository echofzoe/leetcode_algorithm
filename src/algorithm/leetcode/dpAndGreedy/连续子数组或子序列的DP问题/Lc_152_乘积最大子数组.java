package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
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

        System.out.println("数组" + Arrays.toString(nums1) + "中的乘积最大子数组的乘积是" + lc.maxProduct(nums1));
        System.out.println("数组" + Arrays.toString(nums2) + "中的乘积最大子数组的乘积是" + lc.maxProduct(nums2));
        System.out.println("数组" + Arrays.toString(nums3) + "中的乘积最大子数组的乘积是" + lc.maxProduct(nums3));
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
    // - dp[i][j] 表示以 nums[i] 结尾的连续子数组的最值，
    // - 当 j = 0 时，表示计算的是最小值
    // - 当 j = 1 时，表示计算的是最大值
    // - 状态转移方程: {
    //   if (nums[i] >= 0) dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i])
    //   if (nums[i] >= 0) dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i])
    //   if (nums[i] <  0) dp[i][0] = Math.max(dp[i - 1][1] * nums[i], nums[i])
    //   if (nums[i] <  0) dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i])
    // }
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
