package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长递增子序列
 * <P>https://leetcode-cn.com/problems/longest-increasing-subsequence/</P>
 *
 * @author echofzoe
 * @updated 2021.7.26
 * @since unknown
 */
public class Lc_300_最长递增子序列 {

    public static void main(String[] args) {
        Lc_300_最长递增子序列 lc = new Lc_300_最长递增子序列();

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。\n" +
                "子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.lengthOfLIS(nums));
    }

    // 贪心 + 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    // - 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[] d = new int[n];
        int idx = 0;
        d[idx] = nums[0];

        for (int i = 1; i < n; i++) {
            if (d[idx] < nums[i]) {
                d[++idx] = nums[i];
            } else {
                int lo = 0, mid, hi = idx, pos = -1;
                while (lo < hi) {
                    mid = lo + (hi - lo) / 2;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }

                d[pos + 1] = nums[i];
            }
        }

        return idx + 1;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 1;

        // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取
        /*
            状态转移方程：{
                dp[i] = max(dp[j]) + 1, 0 <= j < i && nums[j] < nums[i]
            }
         */
        int[] dp = new int[n];
        // base case
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

}
