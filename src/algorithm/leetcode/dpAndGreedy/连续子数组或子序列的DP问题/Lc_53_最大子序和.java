package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最大子序和
 * <P>https://leetcode-cn.com/problems/maximum-subarray/</P>
 *
 * @author echofzoe
 * @updated 2021.7.7
 * @since unknown
 */
public class Lc_53_最大子序和 {

    public static void main(String[] args) {
        Lc_53_最大子序和 lc = new Lc_53_最大子序和();

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.maxSubArray2(nums));
    }

    // dp - 时间复杂度 O(N) - 空间复杂度 O(N) 为dp数组的开销,也可以直接原地修改数组,省下这笔开销从而达到O(1)的空间复杂度
    // - dp[i] 表示以 nums[i] 为结尾的连续子数组的最大和
    // - 状态转移方程: {
    //   dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])
    // }
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        // dp[i] 表示以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            dp[i] = Math.max(dp[i - 1] + cur, cur);
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maxSubArray1(int[] nums) {
        int n = nums.length;

        int cur = nums[0], res = nums[0];

        for (int i = 1; i < n; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            res = Math.max(cur, res);
        }

        return res;
    }

    // 分治 - 时间复杂度 O(N) - 空间复杂度 O(logN)
    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    private Status getInfo(int[] nums, int l, int r) {
        if (l == r) return new Status(nums[l], nums[l], nums[l], nums[l]);

        int m = l + (r - l) / 2;
        Status lSub = getInfo(nums, l, m);
        Status rSub = getInfo(nums, m + 1, r);

        return pushUp(lSub, rSub);
    }

    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(l.rSum + r.lSum, Math.max(l.mSum, r.mSum));
        return new Status(lSum, rSum, mSum, iSum);
    }

    private static class Status {
        /*
         * lSum 表示 [l,r] 内以 l 为左端点的最大子段和
         * rSum 表示 [l,r] 内以 r 为右端点的最大子段和
         * mSum 表示 [l,r] 内的最大子段和
         * iSum 表示 [l,r] 的区间和
         */
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

}
