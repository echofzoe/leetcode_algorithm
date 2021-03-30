package algorithm.leetcode.bit;

public class Lc_190_颠倒二进制位 {

    // 颠倒二进制位
    // https://leetcode-cn.com/problems/reverse-bits/

    public static void main(String[] args) {
        Lc_190_颠倒二进制位 lc = new Lc_190_颠倒二进制位();
        int input = 43261596;

        System.out.println("将数字" + input + "的二进制位颠倒后得到的结果是" + lc.reverseBits(input));
    }

    // 位运算 - 时间复杂度 O(1) 最多执行32次 - 空间复杂度 O(1)
    public int reverseBits(int n) {
        int res = 0, p = 31;
        while (n != 0) {
            // 左移都是逻辑位移，右移分为逻辑位移和算术位移。这就是为什么左移不分，右移有两种的原因
            // 因为左移是在后面补0，而右移是在前面补1或0，有无符号是取决于数的前面的第一位是0还是1，
            // 所以右移是会产生到底补1还是0的问题，而左移始终是在右边补，不会产生符号问题。
            // 所以没有必要无符号左移 <<<。无符号左移 <<< 和左移 << 是一样的概念

            res += (n & 1) << p;
            n >>>= 1;
            p -= 1;
        }

        return res;
    }

    public int reverseBits1(int n) {
        StringBuilder numStr = new StringBuilder();
        // 转成二进制字符串，此时已经逆转
        while ((n / 2) != 0) {
            numStr.append(n % 2);
            n /= 2;
        }
        // 因为上面的判断条件少算了一次，这里加一次
        numStr.append(n % 2);

        // 不足 32 位，在前面补0，因为反转了字符串，所以应在后面补0
        while (32 - numStr.length() > 0) {
            numStr.append("0");
        }

        // 二进制转十进制并返回 - 方式1
        return Integer.parseInt(numStr.toString(), 2);

        // 二进制转十进制并返回 - 方式2
//        return binaryToDecimal(numStr.toString());
    }

    private int binaryToDecimal(String binNum) {
        if (binNum.length() != 32) return 0;

        // 十进制数
        int decimal = 0;
        // 幂次
        int p = 0;

        for (int i = binNum.length() - 1; i >= 0; i--) {
            decimal += Integer.parseInt(String.valueOf(binNum.charAt(i))) * Math.pow(2, p);
            p++;
        }

        return decimal;
    }
}
