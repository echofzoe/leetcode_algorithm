package algorithm.leetcode.周赛.lc_2021_7_4_NIO;

/**
 * 统计好数字的数目
 * <P>https://leetcode-cn.com/problems/count-good-numbers/</P>
 *
 * @author echofzoe
 * @since 2021.7.4
 */
public class Lc_5802_统计好数字的数目 {

    public static void main(String[] args) {
        Lc_5802_统计好数字的数目 lc = new Lc_5802_统计好数字的数目();

        int n = 17;

        System.out.println("我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）偶数 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5 或 7）。\n" +
                "  - 比方说，\"2582\" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。但 \"3245\" 不是 好数字，因为 3 在偶数下标处但不是偶数。\n" +
                "给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串 总数 。由于答案可能会很大，请你将它对 109 + 7 取余后返回 。\n" +
                "一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包含前导 0 。\n");
        System.out.println("输入：n = " + n);
        System.out.println("输出：" + lc.countGoodNumbers(n));  // 999999111
    }

    // 快速幂 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    private final int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        long a = n / 2, b = n & 1;

        return b == 0 ? (int) pow(20, a) : (int) (pow(20, a) * 5 % MOD);
    }

    private long pow(long x, long p) {
        if (x == 0L) return 0;
        if (x == 1L || p == 0) return 1;

        long res = 1L;

        while (p > 0) {
            if ((p & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            p >>= 1;
        }

        return res;
    }

}
