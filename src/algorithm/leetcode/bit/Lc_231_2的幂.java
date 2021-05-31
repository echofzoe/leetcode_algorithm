package algorithm.leetcode.bit;

/**
 * 2 的幂
 * <P>https://leetcode-cn.com/problems/power-of-two/</P>
 *
 * @author echofzoe
 * @since 2021.5.31
 */
public class Lc_231_2的幂 {

    public static void main(String[] args) {
        Lc_231_2的幂 lc = new Lc_231_2的幂();

        int n = 16;

        System.out.println("给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。\n" +
                "如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。");
        System.out.println("输入：n = " + n);
        System.out.println("输出：" + lc.isPowerOfTwo(n));
    }

    // 位运算 - 时间复杂度 O(1) - 空间复杂度 O(1)
    public boolean isPowerOfTwo(int n) {
//        return n > 0 && (n & (n - 1)) == 0;
        return n > 0 && (n & -n) == n;
    }

}
