package algorithm.leetcode.dfsAndBfs;

public class Lc_115_不同的子序列 {

    // 不同的子序列
    // https://leetcode-cn.com/problems/distinct-subsequences/

    public static void main(String[] args) {
        Lc_115_不同的子序列 lc = new Lc_115_不同的子序列();
        String s = "rabbbit", t = "rabbit";

        System.out.println("有" + lc.numDistinctDP2(s, t) + "种可以从\"" + s + "\"的子序列中得到\"" + t + "\"的方案");
    }

    // DFS - 时间复杂度 O() - 空间复杂度 O()
    public int numDistinctDFS(String s, String t) {
        char[] sc = s.toCharArray(), tc = t.toCharArray();
        return dfs(sc, tc, 0, 0);
    }

    private int dfs(char[] sc, char[] tc, int i, int j) {
        int m = sc.length, n = tc.length;

        // 剪枝
        if (m - i < n - j) return 0;
        if (j == n) return 1;

        if (sc[i] == tc[j]) {
            // 考虑sc[i]和tc[j]匹配 + 不考虑sc[i]和tc[j]匹配
            return dfs(sc, tc, i + 1, j + 1) + dfs(sc, tc, i + 1, j);
        } else {
            // sc[i]和tc[j]不匹配
            return dfs(sc, tc, i + 1, j);
        }
    }

    // DP - 时间复杂度 O(MN) - 空间复杂度 O(MN)
    public int numDistinctDP(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return 0;

        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 0; i < n; i++) {
            dp[m][i] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        // 状态转移方程
        // - 当 s[i] == t[j] 时，dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j]
        // - 当 s[i] != t[j] 时，dp[i][j] = dp[i + 1][j]
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    // DP 空间压缩 - 时间复杂度 O(MN) - 空间复杂度 O(N)
    public int numDistinctDP2(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += j > 0 ? dp[j - 1] : 1;
                }
            }
        }

        return dp[n - 1];
    }

}
