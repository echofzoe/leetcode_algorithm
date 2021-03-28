package algorithm.leetcode.math;

public class Lc_343_整数拆分 {

    // 整数拆分
    // https://leetcode-cn.com/problems/integer-break/

    public static void main(String[] args) {
        Lc_343_整数拆分 lc = new Lc_343_整数拆分();
        int n = 10;

        System.out.println("给定一个正整数 " + n + "，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。则可以获得的最大乘积是" + lc.integerBreakMath(n));
    }

    // 数学 - 时间复杂度 O(1) - 空间复杂度 O(1)
    // 推论一：若拆分的数量 a 确定，则各拆分数字相等时，乘积最大
    // 推论二：将数字 n 尽可能以因子 3 等分时，乘积最大
    public int integerBreakMath(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;

        int a = n / 3, b = n % 3;

        int res = 0;
        if (b == 0) res = pow(3, a);
        else if (b == 1) res = pow(3, a - 1) * 2 * 2;
        else res = pow(3, a) * 2;

        return res;
    }

    private int pow(int x, int n) {
        return n < 0 ? 1 / helper(x, n) : helper(x, n);
    }

    private int helper(int x, int n) {
        if (n == 0) return 1;

        int half = helper(x, n / 2);

        return (n & 1) == 1 ? half * half * x : half * half;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public int integerBreakDP(int n) {
        int[] dp = new int[n + 1];
        // base case
        dp[1] = 0;  // 1是最小的正整数

        for (int i = 2; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                // 将 i 拆分成 j 和 i−j 的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j × (i−j)
                // 将 i 拆分成 j 和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j × dp[i−j]
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }

        return dp[n];
    }

}
