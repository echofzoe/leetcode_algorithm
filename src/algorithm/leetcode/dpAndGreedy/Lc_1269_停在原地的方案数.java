package algorithm.leetcode.dpAndGreedy;

/**
 * 停在原地的方案数
 * <P>https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_1269_停在原地的方案数 {

    public static void main(String[] args) {
        Lc_1269_停在原地的方案数 lc = new Lc_1269_停在原地的方案数();

        int steps = 3, arrLen = 2;

        System.out.println("有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。\n" +
                "每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。\n" +
                "给你两个整数 steps = " + steps + " 和 arrLen = + " + arrLen + "，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。\n" +
                "由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。\n" +
                "方案数为 " + lc.numWays(steps, arrLen));
    }

    // DP - 时间复杂度 O(steps * min(arrLen, steps)) - 空间复杂度 O(steps * min(arrLen, steps))
    /* - dp[i][j]表示在i步操作之后，指针位于下标j的方案数。其中，0 <= i <= steps，0 <= j <= min(arrLen - 1, steps)

       - 初始条件: {
         dp[0][0] = 1
         dp[0][j] = 0, 1 <= j <= min(arrLen - 1, steps)
       }

       - 状态转移方程: {
         dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i - 1][j + 1]
       }

       - 边界情况: {
         dp[i - 1][j - 1] = 0, j = 0
         dp[i - 1][j + 1] = 0, j = min(arrLen - 1, steps)
       }
     */
    public int numWays(int steps, int arrLen) {
        final int MOD = 1000000007;
        int len = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][len + 1];
        // base case
        dp[0][0] = 1;

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= len; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1] % MOD);
                if (j + 1 <= len) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
            }
        }

        return dp[steps][0];
    }

}
