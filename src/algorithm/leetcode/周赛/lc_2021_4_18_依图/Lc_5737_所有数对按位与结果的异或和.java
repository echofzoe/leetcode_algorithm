package algorithm.leetcode.周赛.lc_2021_4_18_依图;

import java.util.Arrays;

/**
 * 所有数对按位与结果的异或和
 * <p>题目链接: https://leetcode-cn.com/problems/find-xor-sum-of-all-pairs-bitwise-and/</p>
 *
 * @author echofzoe
 * @since 2021.4.18
 */
public class Lc_5737_所有数对按位与结果的异或和 {

    public static void main(String[] args) {
        Lc_5737_所有数对按位与结果的异或和 lc = new Lc_5737_所有数对按位与结果的异或和();

        int[] arr1 = {1, 2, 3}, arr2 = {6, 5};

        System.out.println("对于数组" + Arrays.toString(arr1) + "和" + Arrays.toString(arr2) + "，由每个 (i, j) 数对构造成的一个由 arr1[i] AND arr2[j]（按位 AND 运算）结果组成的列表的异或和是" + lc.getXORSum(arr1, arr2));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    // - 算法: {
    //   因为 (a & b1) ^ (a & b2) = a & (b1 ^ b2)
    //   所以 (a1 & b1) ^ (a1 & b2) ^ ... ^ (a1 & bm) ^ ... ^ (an & bm) = [a1 & (b1 ^ b2 ^ ... ^ bm)] ^ ... ^ [an & (b1 ^ b2 ^ ... ^ bm)]
    // }
    public int getXORSum(int[] arr1, int[] arr2) {
        int res = 0, t = 0;

        for (int b : arr2) {
            t ^= b;
        }

        for (int a : arr1) {
            res ^= (a & t);
        }

        return res;
    }

}
