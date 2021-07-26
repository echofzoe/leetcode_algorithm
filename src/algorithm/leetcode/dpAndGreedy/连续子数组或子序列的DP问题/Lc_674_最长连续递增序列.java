package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长连续递增序列
 * <P>https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.7.26
 */
public class Lc_674_最长连续递增序列 {

    public static void main(String[] args) {
        Lc_674_最长连续递增序列 lc = new Lc_674_最长连续递增序列();

        int[] nums = {1,3,5,4,7};
        
        System.out.println("给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。\n" +
                "连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findLengthOfLCIS(nums));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        // dp[i] 表示nums[0:i]中的最长连续递增子数组的长度
        int[] dp = new int[n];
        // base case
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = nums[i - 1] < nums[i] ? dp[i - 1] + 1 : 1;
        }

        return Arrays.stream(dp).max().getAsInt();
    }
    
    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int findLengthOfLCIS1(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int l = 0, r = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                r++;
            } else {
                res = Math.max(res, r - l + 1);
                l = i;
                r = i;
            }
        }

        return Math.max(res, r - l + 1);
    }

}
