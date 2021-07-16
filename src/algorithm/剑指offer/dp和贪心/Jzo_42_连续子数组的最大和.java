package algorithm.剑指offer.dp和贪心;

import java.util.Arrays;

/**
 * 连续子数组的最大和
 * <P>https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/</P>
 *
 * @author echofzoe
 * @since 2021.4.14
 */
public class Jzo_42_连续子数组的最大和 {

    public static void main(String[] args) {
        Jzo_42_连续子数组的最大和 jz = new Jzo_42_连续子数组的最大和();

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("数组" + Arrays.toString(nums) + "中最大的连续子数组元素之和为" + jz.maxSubArray(nums));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 可原地修改源数组，使得空间复杂度优化成O(1)
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        /*
            dp[i] 表示以元素 nums[i] 为结尾的连续子数组的最大和
            状态转移方程: {
              dp[i] = dp[i - 1] + nums[i], dp[i - 1] > 0
              dp[i] = nums[i],             dp[i - 1] <= 0
            }
        */
        int[] dp = new int[n + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i + 1] = nums[i] + Math.max(dp[i], 0);
            res = Math.max(res, dp[i + 1]);
        }

        return res;
    }

}
