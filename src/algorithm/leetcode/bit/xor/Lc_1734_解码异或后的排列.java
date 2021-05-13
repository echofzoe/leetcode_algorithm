package algorithm.leetcode.bit.xor;

import java.util.Arrays;

/**
 * 解码异或后的排列
 * <P>https://leetcode-cn.com/problems/decode-xored-permutation/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_1734_解码异或后的排列 {

    public static void main(String[] args) {
        Lc_1734_解码异或后的排列 lc = new Lc_1734_解码异或后的排列();

        int[] encoded = {6, 5, 4, 6};

        System.out.println("给你一个整数数组 perm ，它是前 n 个正整数的 排列，且 n 是个 奇数 。\n" +
                "它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。\n" +
                "给你 encoded 数组 = " + Arrays.toString(encoded) + "，则原始数组 perm 是 " + Arrays.toString(lc.decode(encoded)));
    }

    // 模拟xor - 时间复杂度 O(N) - 空间复杂度 O(1) 注意空间复杂度不考虑返回值
    public int[] decode(int[] encoded) {
        int n = encoded.length;

        /* 已知 encoded.length % 2 = 0
           已知 encoded[i] = perm[i] ^ perm[i + 1], encoded[i + 2] = perm[i + 2] ^ perm[i + 3], ...
           设 xorP =  perm[0] ^ ... ^ perm[perm.length] = 1 ^ ... ^ n
           设 xorE = encoded[1] ^ encoded[3] ^ ... = perm[1] ^ perm[2] ^ perm[3] ^ ...
           => perm[0] = xorP ^ xorE
           已知 perm[0] ^ perm[1] = encoded[0]
           => perm[1] = encoded[0] ^ perm[0], perm[2] = encoded[1] ^ perm[1], ...
         */
        int xorP = 0, xorE = 0;
        for (int i = 1; i <= n + 1; i++) xorP ^= i;
        for (int i = 1; i < n; i += 2) xorE ^= encoded[i];

        int[] perm = new int[n + 1];
        perm[0] = xorP ^ xorE;
        for (int i = 1; i <= n; i++) {
            perm[i] = encoded[i - 1] ^ perm[i - 1];
        }

        return perm;
    }

}
