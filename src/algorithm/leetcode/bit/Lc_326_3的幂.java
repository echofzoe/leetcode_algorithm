package algorithm.leetcode.bit;

/**
 * 3的幂
 * <P>https://leetcode-cn.com/problems/power-of-three/</P>
 *
 * @author echofzoe
 * @since 2021.6.7
 */
public class Lc_326_3的幂 {

    public static void main(String[] args) {
        Lc_326_3的幂 lc = new Lc_326_3的幂();

        int n = 27;
        
        System.out.println("给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。\n" +
                "整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x。\n");
        System.out.println("输入：n = " + n);
        System.out.println("输出：" + lc.isPowerOfThree(n));
    }

    // 迭代 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

}
