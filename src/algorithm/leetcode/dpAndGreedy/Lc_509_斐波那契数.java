package algorithm.leetcode.dpAndGreedy;

public class Lc_509_斐波那契数 {

    // 斐波那契数
    // - F(0) = 0, F(1) = 1, F(N) = F(N - 1) + F(N - 2), 其中 N > 1
    // https://leetcode-cn.com/problems/fibonacci-number/

    public static void main(String[] args) {
        Lc_509_斐波那契数 lc = new Lc_509_斐波那契数();
        int n1 = 3;
        int n2 = 10;
        int n3 = 20;
        int n4 = 30;

        System.out.println("斐波那契数列 F(" + n1 + ") = " + lc.fibRecursive(n1));
        System.out.println("斐波那契数列 F(" + n2 + ") = " + lc.fibRecursiveWithMemory(n2));
        System.out.println("斐波那契数列 F(" + n3 + ") = " + lc.fibDp(n3));
        System.out.println("斐波那契数列 F(" + n4 + ") = " + lc.fibDp(n4));
    }

    // 递归 - 时间复杂度 O(2^N) 存在大量重复计算 - 空间复杂度 O(N) 为递归栈的开销
    public int fibRecursive(int N) {
        // base case
        if (N < 2) return N;

        return fibRecursive(N - 1) + fibRecursive(N - 2);
    }

    // 递归 + 备忘录 自顶向下 - 时间复杂度 O(N) - 空间复杂度 O(N) 为递归栈的开销
    public int fibRecursiveWithMemory(int N) {
        if (N == 0) return 0;

        // 初始化备忘录
        int[] memo = new int[N + 1];

        // 进行带备忘录的递归
        return helper(memo, N);
    }

    private int helper(int[] memo, int N) {
        // base case
        if (N < 2) return N;

        // 已经计算过
        if (memo[N] != 0) return memo[N];

        // 递归
        memo[N] = helper(memo, N - 1) + helper(memo, N - 2);

        return memo[N];
    }

    // dp + 迭代 自底向上 - 时间复杂度 O(N) - 空间复杂度 O(N) 为dp数组的开销
    public int fibDp(int N) {
        // 特判
        if (N < 2) return N;

        int[] dp = new int[N + 1];
        // base case
        dp[1] = 1;    // 初始化默认 dp[0] = 0

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[N];
    }

    // dp + 迭代 优化空间复杂度 - 时间复杂度 O(N) - 空间复杂度 O(1) 只保存前两个状态
    public int fibDpOptimization(int N) {
        // 特判
        if (N < 2) return N;

        // base case
        int s1 = 0, s2 = 1;    // 前两个status

        for (int i = 3; i <= N; i++) {
            int sum = s1 + s2;
            s1 = s2;
            s2 = sum;
        }

        return s2;
    }

}
