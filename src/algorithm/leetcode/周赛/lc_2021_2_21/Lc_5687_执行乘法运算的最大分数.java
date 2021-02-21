package algorithm.leetcode.周赛.lc_2021_2_21;

public class Lc_5687_执行乘法运算的最大分数 {

    // 执行乘法运算的最大分数
    // https://leetcode-cn.com/problems/maximum-score-from-performing-multiplication-operations/

    public static void main(String[] args) {
        Lc_5687_执行乘法运算的最大分数 lc = new Lc_5687_执行乘法运算的最大分数();
        int[] nums = {-5, -3, -3, -2, 7, 1};
        int[] multipliers = {-10, -5, 3, 4, 6};

        System.out.println("按题意执行multipliers.length步操作后，能得到的最大分数是" + lc.maximumScore(nums, multipliers));
    }

    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;

        int[][] dp = new int[m + 1][m + 1];
        // 状态转移方程：
        // - i = 0 && j > 1时，dp[i][j] = dp[i][j - 1] + (mul * nums[n - j]))
        // - i > 1 && j = 0时，dp[i][j] = dp[i - 1][j] + (mul * nums[i]))
        // - i > 1 && j > 1时，dp[i][j] = max(dp[i - 1][j] + (mul * nums[i]), dp[i][j - 1] + (mul * nums[n - j]))
        // base case
        dp[1][0] = nums[0] * multipliers[0];
        dp[0][1] = nums[n - 1] * multipliers[0];

        for (int i = 2; i <= m; i++) {
            int mul = multipliers[i - 1];

            for (int left = 0; left <= i; left++) {
                int right = i - left;

                if (left == 0) {
                    dp[left][right] = dp[left][right - 1] + (mul * nums[n - right]);
                    continue;
                }

                if (right == 0) {
                    dp[left][right] = dp[left - 1][right] + (mul * nums[left - 1]);
                    continue;
                }

                dp[left][right] = Math.max(dp[left - 1][right] + (mul * nums[left - 1]), dp[left][right - 1] + (mul * nums[n - right]));
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            res = Math.max(dp[i][m - i], res);
        }

        return res;
    }

}
