package algorithm.leetcode.doublePoint;

import algorithm.leetcode.utils.NumsUtils;

import java.util.Arrays;

public class Lc_922_按奇偶排序数组II {

    // 按奇偶排序数组 II
    // https://leetcode-cn.com/problems/sort-array-by-parity-ii/

    public static void main(String[] args) {
        Lc_922_按奇偶排序数组II lc = new Lc_922_按奇偶排序数组II();
        int[] A = {4, 2, 5, 7};

        System.out.println("对一半是奇数, 一半是偶数的非负整数数组" + Arrays.toString(A) + "进行排序, 使得其奇数下标的元素为奇数、偶数下标的元素为偶数, 排序结果是" + Arrays.toString(lc.sortArrayByParityII(A)));
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] sortArrayByParityII(int[] A) {
        int i = 1;    // 奇数下标
        int j;    // 偶数下标

        for (j = 0; j < A.length; j += 2) {
            if ((A[j] & 1) == 1) {
                while ((A[i] & 1) == 1) {
                    i += 2;
                }
            }
            NumsUtils.swap(A, i, j, true);
        }

        return A;
    }

}
