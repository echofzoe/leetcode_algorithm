package algorithm.leetcode.周赛.lc_2020_12_13_字节;

import java.util.Arrays;
import java.util.Map;

public class Lc_5627_石子游戏VII {

    // 石子游戏 VII
    // https://leetcode-cn.com/problems/stone-game-vii/

    public static void main(String[] args) {
        Lc_5627_石子游戏VII lc = new Lc_5627_石子游戏VII();
        int[] stones1 = {7, 90, 5, 1, 100, 10, 10, 2};
        int[] stones2 = {5, 3, 1, 4, 2};

        System.out.println(Arrays.toString(stones1) + "按题意得到的得分差值为" + lc.stoneGameVIIRecursive(stones1));
        System.out.println(Arrays.toString(stones2) + "按题意得到的得分差值为" + lc.stoneGameVIIDp(stones2));
    }

    int[][] memo;

    // 递归 + 记忆化 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int stoneGameVIIRecursive(int[] stones) {
        int n = stones.length;
        this.memo = new int[n + 1][n + 1];

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
            if (i < n) sum += stones[i];
        }

        return stoneGameVIIRecursive(stones, 0, n - 1, sum);
    }

    private int stoneGameVIIRecursive(int[] stones, int left, int right, int sum) {
        if (left == right) return 0;

        if (memo[left][right] != Integer.MIN_VALUE) return memo[left][right];

        int chooseLeft = sum - stones[left] - stoneGameVIIRecursive(stones, left + 1, right, sum - stones[left]);
        int chooseRight = sum - stones[right] - stoneGameVIIRecursive(stones, left, right - 1, sum - stones[right]);

        int res = Math.max(chooseLeft, chooseRight);
        memo[left][right] = res;

        return res;
    }


    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int stoneGameVIIDp(int[] stones) {
        int n = stones.length;

        int[][] dp = new int[n][n];

        // base case
        dp[0][0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                int a = j - i;
                int b = j;
                if (a + 1 == b) {
                    dp[a][b] = Math.max(stones[a], stones[b]);
                } else if (a + 2 == b) {
                    if (stones[a] < stones[b]) {
                        dp[a][b] = Math.min(stones[b], stones[b - 1]);
                    } else {
                        dp[a][b] = Math.min(stones[a], stones[a + 1]);
                    }
                } else {
                    int chooseA = Math.min(stones[a + 1] + dp[a + 2][b], stones[b] + dp[a + 1][b - 1]);
                    int chooseB = Math.min(stones[a] + dp[a + 1][b - 1], stones[b - 1] + dp[a][b - 2]);
                    dp[a][b] = Math.max(chooseA, chooseB);
                }
            }
        }

        return dp[0][n - 1];
    }

}