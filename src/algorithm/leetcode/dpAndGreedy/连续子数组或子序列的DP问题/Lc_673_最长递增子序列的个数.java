package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * <P>https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.7.28
 */
public class Lc_673_最长递增子序列的个数 {

    public static void main(String[] args) {
        Lc_673_最长递增子序列的个数 lc = new Lc_673_最长递增子序列的个数();

        int[] nums = {1, 3, 5, 4, 7};

        System.out.println("给定一个未排序的整数数组，找到最长递增子序列的个数。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findNumberOfLIS(nums));  // 2
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取
        int[] dp = new int[n];
        // base case
        Arrays.fill(dp, 1);

        // cnt[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度在整个nums[0:i]区间内的LIS中的个数
        int[] cnt = new int[n];
        // base case
        Arrays.fill(cnt, 1);

        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        // LIS的长度增加了，且状态是从dp[j]转移而来，故有多少cnt[j]就有多少新的cnt[i]
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        // LIS的长度没有增加，但出现了长度相同的情况，故将相同长度的cnt[j]加入cnt[i]
                        cnt[i] += cnt[j];
                    }
                }
            }

            // 实时计算以nums[i]结尾的LIS
            max = Math.max(max, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) res += cnt[i];
        }

        return res;
    }

}
