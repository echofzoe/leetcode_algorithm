package algorithm.leetcode.bit;

public class Jzo_15_二进制中1的个数 {

    // 二进制中1的个数
    // https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/

    public static void main(String[] args) {
        Jzo_15_二进制中1的个数 lc = new Jzo_15_二进制中1的个数();
        int n = 0B11111111111111111111111111111101;

        System.out.println(n + " 表示成二进制是 " + Integer.toBinaryString(n) + ", 其中有 " + lc.hammingWeight2(n) + " 个1.");
    }

    // 模拟 - 时间复杂度 O(logN) 逐位判断需循环logN次，其中logN代表数字n最高位1的所在位数（例如 log24=2log_2 4 = 2log2​4=2, log216=4log_2 16 = 4log2​16=4）- 空间复杂度 O(1)
    public int hammingWeight1(int n) {
        int res = 0;

        while (n != 0) {
            res += (n & 1);
            n >>>= 1;
        }

        return res;
    }

    // 模拟 - 时间复杂度 O(1) 最大为32 - 空间复杂度 O(1)
    public int hammingWeight2(int n) {
        int res = 0;

        while (n != 0) {
            res++;
            n &= n - 1;
        }

        return res;
    }
}
