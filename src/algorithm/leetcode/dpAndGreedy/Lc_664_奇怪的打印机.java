package algorithm.leetcode.dpAndGreedy;

/**
 * 奇怪的打印机
 * <P>https://leetcode-cn.com/problems/strange-printer/</P>
 *
 * @author echofzoe
 * @since 2021.5.24
 * @updated 2021.6.17
 */
public class Lc_664_奇怪的打印机 {

    public static void main(String[] args) {
        Lc_664_奇怪的打印机 lc = new Lc_664_奇怪的打印机();

        String s = "aba";

        System.out.println("有台奇怪的打印机有以下两个特殊要求：\n" +
                "  - 打印机每次只能打印由 同一个字符 组成的序列。\n" +
                "  - 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。\n" +
                "给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。");
        System.out.println("输入：s = \"" + s + "\"");
        System.out.println("输出：" + lc.strangePrinter(s));
    }

    // DP - 时间复杂度 O(N^3) - 空间复杂度 O(N^2)
    public int strangePrinter(String s) {
        int n = s.length();

        // dp[i][j] 表示打印机打印字符串区间[i,j]所需的最少打印次数
        /*
            边界条件：{
                1. 打印一个字符的最少打印次数为1
                - dp[i][i] = 1
                2. 因为求最少次数，故除dp[i][i]之外的有效状态都初始化为最大值，方便后面的比较最小
                - dp[i][j] = 0x3f3f3f3f, i < j
            }
            状态转移方程：{
                dp[i][j] = dp[i][j - 1], s[i] == s[j]
                dp[i][j] = min(dp[i][k] + dp[k + 1][j]), i <= k < j && s[i] != s[j]
            }
        */
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) dp[i][j] = 1;
                else dp[i][j] = 0x3f3f3f3f;
            }
        }

        char[] cs = s.toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (cs[i] == cs[j]) dp[i][j] = dp[i][j - 1];
                else {
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

}
