package algorithm.leetcode.周赛.lc_2021_5_16_蓝湖;

/**
 * 恰有 K 根木棍可以看到的排列数目
 * <P>https://leetcode-cn.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible/</P>
 *
 * @author echofzoe
 * @since 2021.5.16
 */
public class Lc_5762_恰有K根木棍可以看到的排列数目 {

    public static void main(String[] args) {
        Lc_5762_恰有K根木棍可以看到的排列数目 lc = new Lc_5762_恰有K根木棍可以看到的排列数目();

        int n = 20, k = 11;

        System.out.println("有 n 根长度互不相同的木棍，长度为从 1 到 n 的整数。请你将这些木棍排成一排，并满足从左侧 可以看到 恰好 k 根木棍。从左侧 可以看到 木棍的前提是这个木棍的 左侧 不存在比它 更长的 木棍。\n" +
                "- 例如，如果木棍排列为 [1,3,2,5,4] ，那么从左侧可以看到的就是长度分别为 1、3 、5 的木棍。\n" +
                "给你 n 和 k ，返回符合题目要求的排列 数目 。由于答案可能很大，请返回对 109 + 7 取余 的结果。");
        System.out.println("输入： n = " + n + ", k = " + k);
        System.out.println("输出：" + lc.rearrangeSticks(n, k));  // 647427950
    }

    // DP - 时间复杂度 O(N*K) - 空间复杂度 O(N*K)
    public int rearrangeSticks(int n, int k) {
        final long MOD = 1000000007;

        // dp[i][j] 表示共i根棍子时能从最左边看到其中j根棍子的方案数
        /* - 1. 如果最后一根棍子可以被看到，则最后一根棍子的长度必为i
             因此前i-1根棍子的长度为1~(i-1)的一个排列，并且可以看到其中的j-1根棍子，即 dp[i - 1][j - 1]
           - 2. 如果最后一根棍子不可以被看到，则最后一根棍子的长度必为1~(i-1)中的某个数，设为x
             因此前i-1根棍子的长度为1,2,...,x-1,x+1,...,i的一个排列，并且可以看到其中的j根棍子，即 dp[i - 1][j] * (i - 1)
           - 3. 状态转移方程：
             dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] * (i - 1)
        */

        long[][] dp = new long[n + 1][k + 1];
        // base case
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i && j <= k; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + (dp[i - 1][j] * (i - 1)) % MOD) % MOD;
            }
        }

        return (int) dp[n][k];
    }

}
