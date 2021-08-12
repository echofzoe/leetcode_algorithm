package algorithm.leetcode.dpAndGreedy.回文串问题;

/**
 * 最长回文子序列
 * <P>https://leetcode-cn.com/problems/longest-palindromic-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.8.12
 */
public class Lc_516_最长回文子序列 {

    public static void main(String[] args) {
        Lc_516_最长回文子序列 lc = new Lc_516_最长回文子序列();

        String s = "cbbd";

        System.out.println("给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。\n" +
                "子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。\n");
        System.out.println("输入：s = " + "\"" + s + "\"");
        System.out.println("输出：" + lc.longestPalindromeSubseq(s));  // 2
    }

    // dp - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        // dp[i][j] 表示nums[i:j]中的最长回文子序列
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;

            char c1 = cs[i];
            for (int j = i + 1; j < n; j++) {
                char c2 = cs[j];

                if (c1 == c2) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

}
