package algorithm.leetcode.dpAndGreedy.二维DP问题;

/**
 * 奇怪的打印机
 * <P>https://leetcode-cn.com/problems/strange-printer/</P>
 *
 * @author echofzoe
 * @since 2021.5.24
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
    /*
        dp[i][j] 表示打印机打印字符串区间[i, j]时需要的最少打印次数
        状态转移方程: {
            dp[i][j] = dp[i][j - 1], s[i] = s[j]
            dp[i][j] = min(dp[i][k] + dp[i + 1][j]), s[1] != s[j], i <= k < j
        }
     */
    public int strangePrinter(String s) {
        int n;
        if (s == null || (n = s.length()) == 0) return 0;
        char[] cs = s.toCharArray();

        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][i] = 1;

                if (cs[i] == cs[j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int a = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        a = Math.min(a, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = a;
                }
            }
        }

        return dp[0][n - 1];
    }

}
