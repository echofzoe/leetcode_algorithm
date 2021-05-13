package algorithm.leetcode.bit.xor;

import java.util.Arrays;

/**
 * 解码异或后的数组
 * <P>https://leetcode-cn.com/problems/decode-xored-array/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_1720_解码异或后的数组 {

    public static void main(String[] args) {
        Lc_1720_解码异或后的数组 lc = new Lc_1720_解码异或后的数组();

        int[] encoded = {6, 2, 7, 3};
        int first = 4;

        System.out.println("未知 整数数组 arr 由 n 个非负整数组成。\n" +
                "经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1]。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。\n" +
                "给你编码后的数组 encoded = " + Arrays.toString(encoded) + " 和原数组 arr 的第一个元素 first（arr[0]） = " + first + "。\n" +
                "则解码后得到的原数组 arr 是（可以证明答案存在并且是唯一的） " + Arrays.toString(lc.decode(encoded, first)));
    }

    // xor - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;

        /* 已知 encoded = [a, b, c], arr[0] = x
           因 arr[0] ^ arr[1] = encoded[0]
           => arr[0] ^ arr[1] ^ arr[0] = encoded[0] ^ arr[0]
           => arr[1] = encoded[0] ^ arr[0]
         */

        int[] arr = new int[n + 1];
        arr[0] = first;

        for (int i = 1; i <= n; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }

        return arr;
    }

}
