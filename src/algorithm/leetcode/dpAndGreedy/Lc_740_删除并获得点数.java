package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

/**
 * 删除并获得点数
 * <P>https://leetcode-cn.com/problems/delete-and-earn/</P>
 *
 * @author echofzoe
 * @since 2021.5.14
 */
public class Lc_740_删除并获得点数 {

    public static void main(String[] args) {
        Lc_740_删除并获得点数 lc = new Lc_740_删除并获得点数();

        int[] nums = {2, 2, 3, 3, 3, 4};

        System.out.println("给你一个整数数组 nums = " + Arrays.toString(nums) + "，你可以对它进行一些操作。\n" +
                "每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。\n" +
                "开始你拥有 0 个点数。则你能通过这些操作获得的最大点数是 " + lc.deleteAndEarn(nums));
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
    /* - dp[i]
       - 状态转移方程: {
         dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
       }
     */
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int max = 0;
        for (int i : nums) max = Math.max(max, i);

        int[] bucket = new int[max + 1];
        for (int i : nums) bucket[i]++;

        int[] dp = new int[max + 1];
        // base case
        dp[0] = 0;
        dp[1] = bucket[1] * 1;

        int res = dp[1];
        for (int i = 2; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + bucket[i] * i);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

}
