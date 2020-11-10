package algorithm.leetcode.numericalOperations;

public class Lc_50_Pow {

    // Pow(x, n)
    // https://leetcode-cn.com/problems/powx-n/

    public static void main(String[] args) {
        Lc_50_Pow lc = new Lc_50_Pow();
        double input1 = 2.00000, input2 = 2.10000;
        int n1 = 0, n2 = 3;

        System.out.println(input1 + "的" + n1 + "次方是" + lc.myPowRecursive(input1, n1) + ", " + input2 + "的" + n2 + "次方是" + lc.myPowIteration(input2, n2));
    }

    // 快速幂 + 递归 - 时间复杂度 O(logN) 为递归的层数 - 空间复杂度 O(logN) 为递归的层数
    public double myPowRecursive(double x, int n) {
        if (x == 0.0f) return 0.0d;
        if (n == 0 || x == 1) return 1;

        return n < 0 ? 1 / helper(x, -n) : helper(x, n);
    }

    private double helper(double x, int n) {
        if (n == 1) return x;

        double half = helper(x, n / 2);

        return (n & 1) == 1 ? half * half * x : half * half;
    }

    // 快速幂 + 迭代 - 时间复杂度 O(logN) 为迭代的次数 - 空间复杂度 O(1)
    public double myPowIteration(double x, int n) {
        if (x == 0.0f) return 0.0d;
        if (n == 0 || x == 1.0d) return 1.0d;

        double res = 1.0d;

        long b = n;    // 幂次
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        while (b > 0) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }

        return res;
    }

}
