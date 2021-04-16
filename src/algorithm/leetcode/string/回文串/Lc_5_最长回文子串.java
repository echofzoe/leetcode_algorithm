package algorithm.leetcode.string.回文串;

/**
 * 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author echofzoe
 * @since 2021.4.16
 */
public class Lc_5_最长回文子串 {

    public static void main(String[] args) {
        Lc_5_最长回文子串 lc = new Lc_5_最长回文子串();

        String s = "babad";

        System.out.println("字符串\"" + s + "\"中的最长回文子串是" + lc.longestPalindrome(s));
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    // - dp[i][j] 表示 s.substring(i,j+1) 是否是回文串
    // - 状态转移方程: {
    //   dp[i][j] = dp[i - 1][j + 1] || s.charAt(i) == s.charAt(j)
    // }
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        boolean[][] dp = new boolean[n][n];
        // base case
        for (int i = 0; i < n; i++) dp[i][i] = true;

        char[] cs = s.toCharArray();
        int begin = 0, maxLen = 1;

        // 枚举子串长度 - 从长度2开始枚举，因为长度为1的子串必为回文串
        for (int i = 2; i <= n; i++) {
            // 枚举左边界
            for (int lo = 0; lo < n; lo++) {
                // 确定右边界
                int hi = lo + i - 1;
                if (hi >= n) break;

                if (cs[lo] == cs[hi]) {
                    if (hi - lo + 1 <= 3) dp[lo][hi] = true;
                    else dp[lo][hi] = dp[lo + 1][hi - 1];
                } else {
                    dp[lo][hi] = false;
                }

                if (dp[lo][hi] && hi - lo + 1 > maxLen) {
                    begin = lo;
                    maxLen = hi - lo + 1;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

}
