package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

public class Lc_300_最长递增子序列 {

    // 最长递增子序列
    // https://leetcode-cn.com/problems/longest-increasing-subsequence/

    public static void main(String[] args) {
        Lc_300_最长递增子序列 lc = new Lc_300_最长递增子序列();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(Arrays.toString(nums) + "中的最长递增子序列的长度是" + lc.lengthOfLISDP(nums));
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

        // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取
        /*
            状态转移方程：{
                dp[i] = max(dp[j]) + 1, 0<=j<i && nums[j]<nums[i]
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

        return Arrays.stream(dp).max().orElse(1);
    }

}
