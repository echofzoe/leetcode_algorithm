package algorithm.leetcode.bit;

/**
 * 4的幂
 * <P>https://leetcode-cn.com/problems/power-of-four/</P>
 *
 * @author echofzoe
 * @since 2021.5.31
 */
public class Lc_342_4的幂 {

    public static void main(String[] args) {
        Lc_342_4的幂 lc = new Lc_342_4的幂();

        int n = 16;

        System.out.println("给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。\n" +
                "整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x。");
        System.out.println("输入：n " + n);
        System.out.println("输出：" + lc.isPowerOfFour(n));
    }

    // 位运算 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public boolean isPowerOfFour(int n) {
//        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
        return n > 0 && (n & -n) == n && (n & 0xaaaaaaaa) == 0;
    }

}
