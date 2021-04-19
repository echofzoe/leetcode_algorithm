package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

/**
 * 面试题 17.16. 按摩师
 * <P>https://leetcode-cn.com/problems/the-masseuse-lcci/</P>
 *
 * @author echofzoe
 * @since 2021.4.19
 */
public class Lc_面试题_17_16_按摩师 {

    public static void main(String[] args) {
        Lc_面试题_17_16_按摩师 lc = new Lc_面试题_17_16_按摩师();

        int[] nums = {2, 1, 4, 5, 3, 1, 1, 3};

        System.out.println("预约请求" + Arrays.toString(nums) + "中能够得到的最优预约集合（总预约时间最长）是" + lc.massage(nums));  // 12
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N) 为dp数组的O(2N)开销，可优化到O(1)
    // - dp[i][0] 表示第i天不接预约，dp[i][1] 表示第i天接预约
    // - 状态转移方程: {
    //   dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1])
    //   dp[i][1] = dp[i - 1][0] + nums[i]
    // }
    public int massage(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], dp[i][1]);
            dp[i + 1][1] = dp[i][0] + nums[i];
        }

        return Math.max(dp[n][0], dp[n][1]);
    }

}
