package algorithm.剑指offer.dp和贪心;

public class Jzo_10II_青蛙跳台阶问题 {

    // 剑指 Offer 10- II. 青蛙跳台阶问题
    // - 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    // https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/

    public static void main(String[] args) {
        Jzo_10II_青蛙跳台阶问题 lc = new Jzo_10II_青蛙跳台阶问题();
        int n = 7;

        System.out.println("一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。该青蛙跳上一个" + n + "级的台阶总共有" + lc.fibRecursiveWithMemory(n) + "种跳法。");
    }

    // dp + 迭代 优化空间复杂度 - 时间复杂度 O(N) - 空间复杂度 O(1) 只保存前两个状态
    public int numWaysDp(int n) {
        // 特判
        if (n == 0) return 1;

        // base case
        int s1 = 1, s2 = 1;    // 前两个status

        for (int i = 2; i <= n; i++) {
            int sum = (s1 + s2) % 1000000007;
            s1 = s2;
            s2 = sum;
        }

        return s2;
    }

    // 递归 + 备忘录 自顶向下 - 时间复杂度 O(N) - 空间复杂度 O(N) 为递归栈的开销
    public int fibRecursiveWithMemory(int n) {
        if (n == 0) return 1;

        int[] memo = new int[n + 1];

        return helper(memo, n);
    }

    private int helper(int[] memo, int n) {
        if (n == 0) return 1;
        if (n < 2) return n;

        if (memo[n] != 0) return memo[n];

        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);

        return memo[n];
    }

}
