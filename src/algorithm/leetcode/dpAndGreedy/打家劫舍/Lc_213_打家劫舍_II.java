package algorithm.leetcode.dpAndGreedy.打家劫舍;

import java.util.Arrays;

/**
 * 打家劫舍 II
 * <P>https://leetcode-cn.com/problems/house-robber-ii/description/</P>
 *
 * @author echofzoe
 * @since 2021.3.26
 */
public class Lc_213_打家劫舍_II {

    public static void main(String[] args) {
        Lc_213_打家劫舍_II lc = new Lc_213_打家劫舍_II();

        int[] nums = {1, 3, 1, 3, 100};

        System.out.println("你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。\n" +
                "同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。\n" +
                "给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.rob(nums));  // 103
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i]表示偷窃到第i间房子时所能得到的最高偷窃金额
    // - 状态转移方程: {
    //   dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
    // }
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        return Math.max(helper(nums, 0, n - 2), helper(nums, 1, n - 1));
    }

    private int helper(int[] nums, int l, int r) {
        int n = r - l + 1;
        if (n == 1) return nums[l];
        if (n == 2) return Math.max(nums[l], nums[l + 1]);

        int a = nums[l], b = Math.max(nums[l], nums[l + 1]), c;
        for (int i = l + 2; i <= r; i++) {
            c = Math.max(a + nums[i], b);
            a = b;
            b = c;
        }

        return b;
    }

}
