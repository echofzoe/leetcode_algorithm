package algorithm.leetcode.dpAndGreedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大整除子集
 * <P>https://leetcode-cn.com/problems/largest-divisible-subset/</P>
 *
 * @author echofzoe
 * @since 2021.4.23
 */
public class Lc_368_最大整除子集 {

    public static void main(String[] args) {
        Lc_368_最大整除子集 lc = new Lc_368_最大整除子集();

        int[] nums = {1, 2, 4, 8};

        System.out.println("无重复的正整数数组" + Arrays.toString(nums) + "中的最大整除子集是" + lc.largestDivisibleSubset(nums));  // [8,4,2,1]
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    // - dp[i] 表示在升序数组里以nums[i]结尾最大整除子集的长度
    // - 状态转移方程: {
    //   dp[i] = max(dp[i], dp[j] + 1), j in [0,i)
    // }
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int[] dp = new int[n];
        // base case
        Arrays.fill(dp, 1);

        int maxSize = 1, maxVal = 666;

        // 1.动态规划找出最大子集的个数、最大子集中的最大整数
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 2.倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxSize--;
                maxVal = nums[i];
            }
        }

        return res;
    }

}
