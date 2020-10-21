package algorithm.leetcode.bit;

public class Jzo_15_二进制中1的个数 {

    // 二进制中1的个数
    // https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/

    public static void main(String[] args) {
        Jzo_15_二进制中1的个数 lc = new Jzo_15_二进制中1的个数();
        int n = 0B11111111111111111111111111111101;

        System.out.println(n + " 表示成二进制是 " + Integer.toBinaryString(n) + ", 其中有 " + lc.hammingWeight(n) + " 个1.");
    }

    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }

        return count;
    }
}
