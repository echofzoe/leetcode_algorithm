package algorithm.leetcode.bit.xor;

import java.util.Arrays;

/**
 * 子数组异或查询
 * <P>https://leetcode-cn.com/problems/xor-queries-of-a-subarray/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_1310_子数组异或查询 {

    public static void main(String[] args) {
        Lc_1310_子数组异或查询 lc = new Lc_1310_子数组异或查询();

        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};

        System.out.println("有一个正整数数组 arr = " + Arrays.toString(arr) + "，现给你一个对应的查询数组 queries = " + Arrays.deepToString(queries) + "，其中 queries[i] = [Li, Ri]。\n" +
                "对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。\n" +
                "则包含了给定查询 queries 所有结果的数组是" + Arrays.toString(lc.xorQueries(arr, queries)));
    }

    // 异或前缀和 - 时间复杂度 O(N + M) - 空间复杂度 O(N)
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;

        int[] xors = new int[n + 1];  // 前缀异或数组
        // base case
        xors[0] = 0;

        for (int i = 1; i <= n; i++) {
            xors[i] = xors[i - 1] ^ arr[i - 1];
        }

        /* 查询 Q(lo, hi)
           = arr[lo] ^ ... ^ arr[hi]
           = (arr[0] ^ ... ^ arr[lo - 1]) ^ (arr[0] ^ ... ^ arr[lo - 1]) ^ (arr[lo] ^ ... ^ arr[hi])
           = (arr[0] ^ ... ^ arr[lo - 1]) ^ (arr[0] ^ ... ^ arr[hi])
           = xors[lo] ^ xors[hi + 1]
         */

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }

        return res;
    }

}
