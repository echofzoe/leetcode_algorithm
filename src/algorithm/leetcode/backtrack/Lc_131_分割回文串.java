package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Lc_131_分割回文串 {

    // 分割回文串
    // https://leetcode-cn.com/problems/palindrome-partitioning/

    public static void main(String[] args) {
        Lc_131_分割回文串 lc = new Lc_131_分割回文串();
        String s = "aab";

        System.out.println("将\"" + s + "\"分割成一些子串，使每个子串都是回文串，所有的分割方案是" + lc.partition2(s));
    }

    // 回溯 + DP - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N^2)
    public List<List<String>> partition1(String s) {
        int n = s.length();

        // DP 预处理回文串
        char[] cs = s.toCharArray();
        // f[i][j] 表示 s.substring(i, j+1) 是否为回文串
        boolean[][] f = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    f[i][j] = true;
                } else {
                    if (j - i + 1 == 2) {
                        // 当 [i,j] 长度为 2 时
                        f[i][j] = cs[i] == cs[j];
                    } else {
                        // 当 [i,j] 长度大于 2 时
                        f[i][j] = cs[i] == cs[j] && f[i + 1][j - 1];
                    }
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();

        dfs1(res, cur, s, 0, f);
        return res;
    }

    private void dfs1(List<List<String>> res, List<String> cur, String s, int lo, boolean[][] f) {
        int n = s.length();
        if (lo == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int hi = lo; hi < n; hi++) {
            if (f[lo][hi]) {
                cur.add(s.substring(lo, hi + 1));
                dfs1(res, cur, s, hi + 1, f);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 回溯 + 记忆化搜索 - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N^2)
    public List<List<String>> partition2(String s) {
        int n = s.length();

        int[][] mem = new int[n][n];

        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();

        dfs2(res, cur, s, 0, mem);
        return res;
    }

    // 记忆化搜索
    private void dfs2(List<List<String>> res, List<String> cur, String s, int lo, int[][] mem) {
        int n = s.length();
        if (lo == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int hi = lo; hi < n; hi++) {
            if (checkPalindrome(s, lo, hi, mem) == 1) {
                cur.add(s.substring(lo, hi + 1));
                dfs2(res, cur, s, hi + 1, mem);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 在遍历回文串的同时设置记忆化数组，记忆化数组 mem[i][j] 值为0表示未搜索，为1表示是回文串，为-1表示不是回文串
    private int checkPalindrome(String s, int lo, int hi, int[][] mem) {
        if (mem[lo][hi] != 0) return mem[lo][hi];

        if (lo == hi) {
            mem[lo][hi] = 1;
        } else if (hi - lo + 1 == 2) {
            mem[lo][hi] = s.charAt(lo) == s.charAt(hi) ? 1 : -1;
        } else {
            mem[lo][hi] = s.charAt(lo) == s.charAt(hi) ? checkPalindrome(s, lo + 1, hi - 1, mem) : -1;
        }

        return mem[lo][hi];
    }

}
