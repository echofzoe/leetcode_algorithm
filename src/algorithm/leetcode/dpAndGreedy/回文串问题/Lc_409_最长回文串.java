package algorithm.leetcode.dpAndGreedy.回文串问题;

/**
 * 最长回文串
 * https://leetcode-cn.com/problems/longest-palindrome/
 *
 * @author echofzoe
 * @since 2021.4.16
 */
public class Lc_409_最长回文串 {

    public static void main(String[] args) {
        Lc_409_最长回文串 lc = new Lc_409_最长回文串();

        String s = "abccccdd";

        System.out.println("通过\"" + s + "\"中的字母构造成的最长回文串的长度是" + lc.longestPalindrome(s));
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(S) S为字符集大小
    public int longestPalindrome(String s) {
        int n = s.length();

        // A~Z : 65~90
        // a~z : 97~122
        int[] cnt = new int[123];

        char[] cs = s.toCharArray();
        for (char c : cs) cnt[c]++;

        int res = 0;
        for (int x : cnt) {
            res += x / 2 * 2;

            if ((x & 1) == 1 && (res & 1) == 0) {
                res++;
            }
        }

        return res;
    }

}
