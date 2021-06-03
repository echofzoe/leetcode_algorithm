package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * <P>https://leetcode-cn.com/problems/palindrome-partitioning/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.6.3
 */
public class Lc_131_分割回文串 {

    public static void main(String[] args) {
        Lc_131_分割回文串 lc = new Lc_131_分割回文串();

        String s = "aab";

        System.out.println("将\"" + s + "\"分割成一些子串，使每个子串都是回文串，所有的分割方案是" + lc.partition1(s));  // [[a, a, b], [aa, b]]
    }

    // 回溯 + DP - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N^2)
    int n;
    String s;
    List<List<String>> res;
    List<String> cur;
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        n = s.length();
        this.s = s;
        res = new ArrayList<>();
        cur = new ArrayList<>();

        // dp[i][j] 表示 s.substring(i, j + 1) 是否是回文串
        dp = new boolean[n][n];

        // dp 预处理源字符串
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (j - i + 1 == 2) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = b && dp[i + 1][j - 1];
                    }
                }
            }
        }

        dfs(0);

        return res;
    }

    private void dfs(int i) {
        if (i == n) {
            // exit
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int j = i; j < n; j++) {
            if (dp[i][j]) {
                // record
                cur.add(s.substring(i, j + 1));

                // dfs
                dfs(j + 1);

                // backtrace
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 回溯 + 记忆化搜索 - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N^2)
    int[][] mem;

    public List<List<String>> partition1(String s) {
        n = s.length();
        this.s = s;
        res = new ArrayList<>();
        cur = new ArrayList<>();
        mem = new int[n][n];

        dfs1(0);

        return res;
    }

    private void dfs1(int i) {
        if (i == n) {
            // exit
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int j = i; j < n; j++) {
            if (check(i, j) == 1) {
                // record
                cur.add(s.substring(i, j + 1));

                // dfs
                dfs1(j + 1);

                // backtrace
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 在遍历回文串的同时设置记忆化数组，记忆化数组 mem[i][j] 值为0表示未搜索，为1表示是回文串，为-1表示不是回文串
    private int check(int i, int j) {
        if (mem[i][j] != 0) return mem[i][j];

        boolean b = s.charAt(i) == s.charAt(j);

        if (i == j) mem[i][j] = 1;
        else if (j - i + 1 == 2) mem[i][j] = b ? 1 : -1;
        else mem[i][j] = b ? check(i + 1, j - 1) : -1;

        return mem[i][j];
    }

}
