package algorithm.leetcode;

import java.math.BigInteger;

public class leetcode_190 {
    public static void main(String[] args) {
        leetcode_190 lt = new leetcode_190();
        int input1 = 43261596;
        int output1 = lt.reverseBits(input1);
        System.out.println(output1);
    }

    public int reverseBits_special(int n) {
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

    public int reverseBits(int n) {
        StringBuffer numArray = new StringBuffer();
        // 转成二进制字符串，此时已经逆转
        while ((n / 2) != 0) {
            numArray.append(n % 2);
            n /= 2;
        }
        numArray.append(n % 2);   // 因为上面的判断条件少算了一次，这里加一次

        // 不足 32 位，在前面补 0，因为反转了字符串，所以应在后面补 "0"
        while (32 - numArray.length() > 0) {
            numArray.append("0");
        }

        // 二进制转十进制 - 方式1
        int res = Integer.parseInt(numArray.toString(), 2);

        // 二进制转十进制 - 方式2
//        int res = binaryToDecimal(numArray.toString());

        return res;
    }

    private int binaryToDecimal(String binNum) {
        int decimal = 0;
        int p = 0;

        if (binNum.length() != 0) {
            for (int i = binNum.length() - 1; i >= 0; i--) {
                decimal += Integer.valueOf(String.valueOf(binNum.charAt(i))) * Math.pow(2, p);
                p++;
            }
        }

        return decimal;
    }
}
