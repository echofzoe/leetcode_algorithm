package algorithm.剑指offer.bit;

public class Jzo_16_数值的整数次方 {

    // 数值的整数次方
    // https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/

    public static void main(String[] args) {
        Jzo_16_数值的整数次方 lc = new Jzo_16_数值的整数次方();
        double d1 = 2.10000, d2 = 2.00000;
        int n1 = 3, n2 = -2;

        System.out.println(d1 + "^" + n1 + " = " + lc.myPow(d1, n1) + ", " + d2 + "^" + n2 + " = " + lc.myPow(d2, n2));
    }

    // 快速幂 - 时间复杂度 O(log2N) - 空间复杂度 O(1)
    public double myPow(double x, int n) {
        if (x == 0) return 0;

        long power = n;    // 幂次
        double res = 1.0;

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        while (power > 0) {
            if ((power & 1) == 1) {
                // 奇数次 - 幂次减一转换成偶数次
                res *= x;
            }
            x *= x;    // x = x^2;
            power >>= 1;    // b = b/2; 下取整
        }

        return res;
    }
}
