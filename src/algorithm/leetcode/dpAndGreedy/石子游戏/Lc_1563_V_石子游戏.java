package algorithm.leetcode.dpAndGreedy.石子游戏;

import java.util.Arrays;

public class Lc_1563_V_石子游戏 {

    // 石子游戏 V
    // https://leetcode-cn.com/problems/stone-game-v/

    public static void main(String[] args) {
        Lc_1563_V_石子游戏 lc = new Lc_1563_V_石子游戏();
        int[] stoneValue = {6, 2, 3, 4, 5, 5}; // 18

        System.out.println("按照题意操作数组" + Arrays.toString(stoneValue) + "得到的结果是" + lc.stoneGameV(stoneValue));
    }

    // 区间DP - 时间复杂度 O(N^3) - 空间复杂度 O(N^2)
    int[][] f;
    int[] preSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        f = new int[n][n];

        preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stoneValue[i - 1];
        }

        return dfs(stoneValue, 0, n - 1);
    }

    private int dfs(int[] stoneValue, int lo, int hi) {
        if (lo == hi) return 0;
        if (f[lo][hi] != 0) return f[lo][hi];

        // 枚举每一个位置分割位置
        for (int i = lo; i < hi; i++) {
            int sumLo = preSum[i + 1] - preSum[lo];
            int sumHi = preSum[hi + 1] - preSum[i + 1];

            if (sumLo < sumHi) {
                f[lo][hi] = Math.max(f[lo][hi], dfs(stoneValue, lo, i) + sumLo);
            } else if (sumLo > sumHi) {
                f[lo][hi] = Math.max(f[lo][hi], dfs(stoneValue, i + 1, hi) + sumHi);
            } else {
                f[lo][hi] = Math.max(f[lo][hi], Math.max(dfs(stoneValue, lo, i), dfs(stoneValue, i + 1, hi)) + sumLo);
            }
        }

        return f[lo][hi];
    }

}
