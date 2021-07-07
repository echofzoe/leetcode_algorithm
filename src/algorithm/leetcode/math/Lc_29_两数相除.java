package algorithm.leetcode.math;

/**
 * 两数相除
 * <P>https://leetcode-cn.com/problems/divide-two-integers/</P>
 *
 * @author echofzoe
 * @since 2021.7.7
 */
public class Lc_29_两数相除 {

    public static void main(String[] args) {
        Lc_29_两数相除 lc = new Lc_29_两数相除();

        int dividend = 7, divisor = -3;

        System.out.println("给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。\n" +
                "返回被除数 dividend 除以除数 divisor 得到的商。\n" +
                "整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2\n");
        System.out.println("输入：dividend = " + dividend + ", divisor = " + divisor);
        System.out.println("输出：" + lc.divide(dividend, divisor));  // -2
    }

    // 倍增法 - 时间复杂度 O(N) 取决于 dividend/divisor 的大小，为线性复杂度 - 空间复杂度 O(1)
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;

        long a = dividend, b = divisor;
        int sign = (a > 0 && b < 0) || (a < 0 && b > 0) ? -1 : 1;
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;

        long res = div(a, b);
        return sign == 1 ? (res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res) : (int) -res;
    }

    private long div(long a, long b) {
        if (a < b) return 0;

        int cnt = 1;
        long c = b;
        while (c + c <= a) {
            cnt += cnt;
            c += c;
        }

        return cnt + div(a - c, b);
    }

}
