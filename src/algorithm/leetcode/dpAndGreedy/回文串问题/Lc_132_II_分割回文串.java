package algorithm.leetcode.dpAndGreedy.回文串问题;

import java.util.Arrays;

public class Lc_132_II_分割回文串 {

    // 分割回文串 II
    // https://leetcode-cn.com/problems/palindrome-partitioning-ii/

    public static void main(String[] args) {
        Lc_132_II_分割回文串 lc = new Lc_132_II_分割回文串();
        String s = "aab";

        System.out.println("给定一个字符串\"" + s + "\"将他分割成一些子串，要求每个子串都是回文，则符合要求的最少分割次数是" + lc.minCut(s));
    }

    // 两次DP - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    public int minCut(String s) {
        int n = s.length();

        char[] cs = s.toCharArray();
        boolean[][] f = new boolean[n][n];

        for (int hi = 0; hi < n; hi++) {
            for (int lo = 0; lo <= hi; lo++) {
                if (lo == hi) {
                    f[lo][hi] = true;
                } else {
                    if (hi - lo + 1 == 2) {
                        f[lo][hi] = cs[lo] == cs[hi];
                    } else {
                        f[lo][hi] = cs[lo] == cs[hi] && f[lo + 1][hi - 1];
                    }
                }
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int hi = 0; hi < n; hi++) {
            if (f[0][hi]) {
                dp[hi] = 0;
            } else {
                for (int lo = 0; lo < hi; lo++) {
                    if (f[lo + 1][hi]) {
                        dp[hi] = Math.min(dp[hi], dp[lo] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }

}
