package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长递增子序列
 * <P>https://leetcode-cn.com/problems/longest-increasing-subsequence/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.27
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
        if (n <= 1) return n;

        int[] aux = new int[n];
        // base case
        aux[0] = nums[0];

        int end = 0;

        for (int x : nums) {
            // 当 nums[i] 大于 aux 数组的末尾值时，说明当前值可以插入到 aux 中形成一个更长的上升子序列
            if (aux[end] < x) {
                aux[++end] = x;
            }
            // 当 nums[i] 等于 aux 数组的末尾值时，什么也不做，因为以 nums[i] 结尾的最长上升子序列已存在
            // 当 nums[i] 小于 aux 数组的末尾值时，在 aux 中二分查找合适的插入位置 x 并将 aux[x] 替换成 nums[i]
            else {
                int l = 0, m, r = end;

                while (l < r) {
                    m = l + (r - l) / 2;

                    if (aux[m] < x) l = m + 1;
                    else r = m;
                }

                // 当前只让第 1 个严格大于 nums[i] 的数 aux[left] 变小，也就是变成 nums[i]
                // 这一步操作是“无后效性”的，只考虑在索引为 left 时做出的最好选择，运用了贪心思想
                aux[l] = x;
            }
        }

        return ++end;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取
        /*
            状态转移方程：{
                dp[i] = max(dp[j]) + 1, 0 <= j < i && nums[j] < nums[i]
            }
         */
        int[] dp = new int[n];
        // base case
        dp[0] = 1;

        int res = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

}
