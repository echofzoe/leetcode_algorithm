package algorithm.leetcode.双周赛.lc_2021_6_26_shopee;

import java.util.Arrays;

/**
 * 最大子序列交替和
 * <P>https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum/</P>
 *
 * @author echofzoe
 * @since 2021.6.27
 */
public class Lc_5782_最大子序列交替和 {

    public static void main(String[] args) {
        Lc_5782_最大子序列交替和 lc = new Lc_5782_最大子序列交替和();

        int[] nums = {6,2,1,2,4,5};
        
        System.out.println("一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。\n" +
                "  - 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。\n" +
                "给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。\n" +
                "一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.maxAlternatingSum(nums));  // 10
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // dp[i][0] 表示以元素nums[i]为结尾的 长度为奇数的 子序列的最大交替和
        // dp[i][1] 表示以元素nums[i]为结尾的 长度为偶数的 子序列的最大交替和
        /*
            初始状态：{
                1. 一个元素自己就可以作为奇数长度子序列
                - dp[i][0] = nums[i], 0 <= i < n
                2. 一个元素自己就不能作为偶数长度子序列
                - dp[i][1] = MIN_VALUE, 0 <= i < n
            }
            状态转移方程：{
                1. 以元素nums[i]结尾的 长度为奇数的 子序列的最大交替和 可以：
                1.1 不选当前元素
                - dp[i][0] = dp[i - 1][0]
                1.2 选当前元素
                - dp[i][0] = dp[i - 1][1] + nums[i]
                1.3 以当前元素另立门户
                - dp[i][0] = nums[i]

                2. 以元素nums[i]结尾的 长度为偶数的 子序列的最大交替和 可以：
                2.1 不选当前元素
                - dp[i][1] = dp[i - 1][1]
                2.2 选当前元素
                - dp[i][1] = dp[i - 1][1] - nums[i]
            }
         */
        long[][] dp = new long[n][2];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][0] = nums[i];
            dp[i][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n; i++) {
            int x = nums[i];
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] + x, x));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - x);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // DP（空间优化） - 时间复杂度 O(N) - 空间复杂度 O(1)
    public long maxAlternatingSum1(int[] nums) {
        // odd -- 当前长度为奇数的子序和的最大值, even -- 当前长度为偶数的子序和的最大值
        long odd = 0, even = Integer.MIN_VALUE;

        for (int x : nums) {
            long t = odd;
            odd = Math.max(odd, Math.max(x, even + x));
            even = Math.max(even, t - x);
        }

        return odd;
    }

}
