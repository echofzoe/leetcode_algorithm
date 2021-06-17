package algorithm.leetcode.recursion;

import java.util.HashMap;

public class Lc_292_NimGame {

    // Nim Game
    // https://leetcode-cn.com/problems/nim-game/

    public static void main(String[] args) {
        Lc_292_NimGame lc = new Lc_292_NimGame();
        int n = 5;

        System.out.print("如果堆中有 " + n + " 块石头，那么你永远");
        if (lc.canWinNim_2(n)) {
            System.out.print("会");
        } else {
            System.out.print("不会");
        }
        System.out.println("赢得比赛");
    }

    // math
    public boolean canWinNim(int n) {
        // return (n % 4) != 0;
        return (n & 3) != 0;
    }

    // brutal force - 时间复杂度太高
    private boolean canWinNim_1(int n) {
        if (n < 4) return true;
        return !canWinNim_1(n - 1) || !canWinNim_1(n - 2) || !canWinNim_1(n - 3);
    }

    // brutal force with memorandum
    private Boolean[] memo;

    private boolean canWinNim_2(int n) {
        memo = new Boolean[n + 1];

        return dfs(n);
    }

    private boolean dfs(int n) {
        if (n < 4) return true;

        if (memo[n] != null) return memo[n];

        // 前三种选择中有一种能让对手输，那么自己就能赢
        memo[n] = (!dfs(n - 1)) || (!dfs(n - 2)) || (!dfs(n - 3));

        return memo[n];
    }

    // dp with O(n) space
    private boolean canWinNim_3(int n) {
        if (n < 4) return true;

        boolean[] dp = new boolean[n + 1];    // 让出 index0
        dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= n; i++) {
            dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];
        }
        return dp[n];
    }

    // dp with O(1) space
    private boolean canWinNim_4(int n) {
        if (n < 4) return true;

        boolean a, b, c, t;
        a = b = c = true;
        t = false;

        for (int i = 4; i <= n; i++) {
            t = !a || !b || !c;
            a = b;
            b = c;
            c = t;
        }
        return t;
    }

}
