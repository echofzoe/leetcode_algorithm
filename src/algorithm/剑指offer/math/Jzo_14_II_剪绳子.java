package algorithm.剑指offer.math;

import java.math.BigInteger;

public class Jzo_14_II_剪绳子 {

    // 剪绳子 II
    // https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/

    public static void main(String[] args) {
        Jzo_14_II_剪绳子 lc = new Jzo_14_II_剪绳子();
        int n = 1000;

        System.out.println("按题意剪长度为" + n + "的绳子能得到的最大乘积是 " + lc.cuttingRope(n));
        System.out.println("按题意剪长度为" + n + "的绳子能得到的最大乘积是 " + lc.cuttingRopeDP(n));
    }

    private final int MOD = 1000000007;

    // 模拟 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;

        int a = n / 3, b = n % 3, c = 1;
        if (b == 1) {
            a--;
            c = 4;
        } else if (b == 2) {
            c = 2;
        }

        return (int) (my3Pow(a) * c % MOD);
    }

    private long my3Pow(int n) {
        long res = 1, x = 3, mod = MOD;

        while (n > 0) {
            if ((n & 1) == 1) res = res * x % mod;
            x = x * x % mod;
            n >>= 1;
        }

        return res;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int cuttingRopeDP(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        // base case
        dp[1] = new BigInteger("0");

        for (int i = 2; i <= n; i++) {
            BigInteger max = new BigInteger("0");
            for (int j = 1; j < i; j++) {
                max = max
                        .max(dp[i - j].multiply(new BigInteger(String.valueOf(j)))
                                .max(new BigInteger(String.valueOf(j * (i - j)))));
            }
            dp[i] = max;
        }

        return dp[n].mod(new BigInteger(String.valueOf(MOD))).intValue();
    }

}
