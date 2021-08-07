package algorithm.leetcode.双周赛.lc_2021_8_8_蓝湖;

import java.util.Arrays;

/**
 * K 次调整数组大小浪费的最小总空间
 * <P>https://leetcode-cn.com/problems/minimum-total-space-wasted-with-k-resizing-operations/</P>
 *
 * @author echofzoe
 * @since 2021.8.8
 */
public class Lc_5828_K次调整数组大小浪费的最小总空间 {

    public static void main(String[] args) {
        Lc_5828_K次调整数组大小浪费的最小总空间 lc = new Lc_5828_K次调整数组大小浪费的最小总空间();

        int[] nums = {13, 46, 42, 45, 35};
        int k = 4;
        
        System.out.println("你正在设计一个动态数组。给你一个下标从 0 开始的整数数组 nums ，其中 nums[i] 是 i 时刻数组中的元素数目。除此以外，你还有一个整数 k ，表示你可以 调整 数组大小的 最多 次数（每次都可以调整成 任意 大小）。\n" +
                "t 时刻数组的大小 sizet 必须大于等于 nums[t] ，因为数组需要有足够的空间容纳所有元素。t 时刻 浪费的空间 为 sizet - nums[t] ，总 浪费空间为满足 0 <= t < nums.length 的每一个时刻 t 浪费的空间 之和 。\n" +
                "在调整数组大小不超过 k 次的前提下，请你返回 最小总浪费空间 。\n" +
                "注意：数组最开始时可以为 任意大小 ，且 不计入 调整大小的操作次数\n");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("输出：" + lc.minSpaceWastedKResizing(nums, k));
    }

    // dp - 时间复杂度 O(N^2 * K) - 空间复杂度 O(NK)
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;

        // dp[i][j] 表示到第i个位置为止，共调整了j次大小时的最小浪费值
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp) Arrays.fill(d, 0x3f3f3f3f);
        // base case
        int r = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            r = Math.max(r, nums[i]);
            sum += nums[i];
            dp[i + 1][0] = r * (i + 1) - sum;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                r = 0;
                sum = 0;

                for (int next = i + 1; next <= n; next++) {
                    r = Math.max(r, nums[next - 1]);
                    sum += nums[next - 1];
                    dp[next][j + 1] = Math.min(dp[next][j + 1], dp[i][j] + r * (next - i) - sum);
                }
            }
        }

        return dp[n][k];
    }

}
