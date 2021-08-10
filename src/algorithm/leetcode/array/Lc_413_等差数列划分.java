package algorithm.leetcode.array;

import java.util.Arrays;

/**
 * 等差数列划分
 * https://leetcode-cn.com/problems/arithmetic-slices/
 *
 * @author echofzoe
 * @date 2021.3.26
 */
public class Lc_413_等差数列划分 {

    public static void main(String[] args) {
        Lc_413_等差数列划分 lc = new Lc_413_等差数列划分();

        int[] nums = {1, 2, 3, 4};

        System.out.println("如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。\n" +
                "  - 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。\n" +
                "给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。\n" +
                "子数组 是数组中的一个连续序列。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.numberOfArithmeticSlicesDP(nums));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    // - dp[i] 表示以 A[i] 为结尾的等差递增子区间的个数
    // -- eg: A = [0, 1, 2, 3, 4]
    // -- dp[2] = 1, [0, 1, 2]
    // -- dp[3] = dp[2] + 1 = 2
    // --- [0, 1, 2, 3],                 // [0, 1, 2] 之后加一个 3
    // --- [1, 2, 3]                     // 新的递增子区间
    // -- dp[4] = dp[3] + 1 = 3
    // --- [0, 1, 2, 3, 4],              // [0, 1, 2, 3] 之后加一个 4
    // --- [1, 2, 3, 4],                 // [1, 2, 3] 之后加一个 4
    // --- [2, 3, 4]                     // 新的递增子区间
    public int numberOfArithmeticSlicesDP(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        int[] dp = new int[n];
        // base case
        dp[0] = dp[1] = 0;

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return Arrays.stream(dp).sum();
    }

}
