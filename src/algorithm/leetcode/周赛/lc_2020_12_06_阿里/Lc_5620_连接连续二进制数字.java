package algorithm.leetcode.周赛.lc_2020_12_06_阿里;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lc_5620_连接连续二进制数字 {

    // 连接连续二进制数字
    // https://leetcode-cn.com/problems/concatenation-of-consecutive-binary-numbers/

    public static void main(String[] args) {
        Lc_5620_连接连续二进制数字 lc = new Lc_5620_连接连续二进制数字();
        int n = 20;

        System.out.println("给你一个整数 " + n + " ，将 1 到 " + n + " 的二进制表示连接起来，连接结果对应的十进制数字对 1e9 + 7 取余的结果是 " + lc.concatenatedBinaryBigInteger(n));
    }

    public int concatenatedBinary(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }

        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < sb.length(); i++) {
            res += sb.charAt(i) - 48;

            if (i != sb.length() - 1) {
                res = (res << 1) % mod;
            }
        }

        return res;
    }

    // BigInteger - 时间复杂度 O(N) 大数超时 - 空间复杂度 O(N)
    public int concatenatedBinaryBigInteger(int n) {
        StringBuilder sb = new StringBuilder();


        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }

        BigInteger mod = new BigInteger("1000000007");
        BigInteger button = new BigInteger("2");
        BigInteger res = new BigInteger("0");
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                BigInteger tmp = button.pow(sb.length() - 1 - i);
                res = res.add(tmp);
            }
        }

        return (int) res.mod(mod).longValue();
    }

}
