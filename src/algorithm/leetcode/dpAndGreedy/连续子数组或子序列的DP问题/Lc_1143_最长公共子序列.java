package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

/**
 * 最长公共子序列
 * <P>https://leetcode-cn.com/problems/longest-common-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.7.26
 */
public class Lc_1143_最长公共子序列 {

    public static void main(String[] args) {
        Lc_1143_最长公共子序列 lc = new Lc_1143_最长公共子序列();

        String text1 = "abcde", text2 = "ace";

        System.out.println("给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。\n" +
                "一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。\n" +
                "  - 例如，\"ace\" 是 \"abcde\" 的子序列，但 \"aec\" 不是 \"abcde\" 的子序列。\n" +
                "两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。\n");
        System.out.println("输入：text1 = " + "\"" + text1 + "\", text2 = " + "\"" + text2 + "\"");
        System.out.println("输出：" + lc.longestCommonSubsequence(text1, text2));  // 3
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();

        // dp[i][j] 表示text1[0:i]与text2[0:j]的最长公共子序列
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n2; j++) {
                char c2 = text2.charAt(j - 1);

                dp[i][j] = c1 == c2 ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n1][n2];
    }

}
