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

        System.out.println("数组" + Arrays.toString(nums) + "中所有为等差数组的子数组的个数是" + lc.numberOfArithmeticSlicesDP(nums));
    }

    // DP - 时间复杂度 O() - 空间复杂度 O()
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
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return Arrays.stream(dp).sum();
    }

    // 暴力 - 时间复杂度 O() - 空间复杂度 O()
    public int numberOfArithmeticSlicesBF(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int d = nums[i + 1] - nums[i];

            for (int j = i + 2; j < n; j++) {
                int k;
                for (k = i + 1; k <= j; k++) {
                    if (nums[k] - nums[k - 1] != d) break;
                }

                if (k > j) res++;
            }
        }

        return res;
    }

}
