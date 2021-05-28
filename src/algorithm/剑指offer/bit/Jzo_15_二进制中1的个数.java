package algorithm.剑指offer.bit;

public class Jzo_15_二进制中1的个数 {

    // 二进制中1的个数
    // https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/

    public static void main(String[] args) {
        Jzo_15_二进制中1的个数 lc = new Jzo_15_二进制中1的个数();
        int n = 0B11111111111111111111111111111101;
        int n1 = 0B11111111111111111111111110111101;

        System.out.println(n + " 表示成二进制是 " + Integer.toBinaryString(n) + ", 其中有 " + lc.hammingWeight2(n) + " 个1.");
        System.out.println(n + " 表示成二进制是 " + Integer.toBinaryString(n1) + ", 其中有 " + lc.hammingWeight3(n1) + " 个1.");
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

    // JDK - 时间复杂度 O(1) - 空间复杂度 O(1)
    public int hammingWeight3(int n) {
        // 0x55555555 = 0101 0101 0101 0101 0101 0101 0101 0101
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        // 0x33333333 = 0011 0011 0011 0011 0011 0011 0011 0011
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        // 0x0f0f0f0f = 0000 1111 0000 1111 0000 1111 0000 1111
        n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
        // 0x00ff00ff = 0000 0000 1111 1111 0000 0000 1111 1111
        n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
        // 0x00ff00ff = 0000 0000 0000 0000 1111 1111 1111 1111
        n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);

        return n;
    }

}
