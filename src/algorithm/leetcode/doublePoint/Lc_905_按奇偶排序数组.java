package algorithm.leetcode.doublePoint;

import algorithm.leetcode.utils.NumsUtils;

import java.util.Arrays;
import java.util.Comparator;

public class Lc_905_按奇偶排序数组 {

    // 按奇偶排序数组
    // https://leetcode-cn.com/problems/sort-array-by-parity/

    public static void main(String[] args) {
        Lc_905_按奇偶排序数组 lc = new Lc_905_按奇偶排序数组();
        int[] A = {3, 1, 2, 4};

        System.out.println("对非负整数数组" + Arrays.toString(A) + "进行排序, 使得其所有偶数元素之后跟着所有奇数元素, 排序结果是" + Arrays.toString(lc.sortArrayByParityQuicklySort(A)));
    }

    // 暴力 从头遍历 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int[] sortArrayByParityDoublePoint(int[] A) {
        int i;    // 偶数下标
        int j;    // 奇数下标

        for (i = 0; i < A.length; i++) {
            if ((A[i] & 1) == 1) {
                j = i + 1;
                while (j < A.length && (A[j] & 1) == 1) {
                    j++;
                }

                if (j < A.length) {
                    NumsUtils.swap(A, i, j, true);
                }
            }
        }

        return A;
    }

    // 快速排序 两头遍历 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] sortArrayByParityQuicklySort(int[] A) {
        int i = 0, j = A.length - 1;

        while (i < j) {
            if ((A[i] & 1) > (A[j] & 1)) {
                // 前奇后偶即交换
                NumsUtils.swap(A, i, j, true);
            }

            if ((A[i] & 1) == 0) i++;    // i的位置正确
            if ((A[j] & 1) == 1) j--;    // j的位置正确
        }

        return A;
    }

    // 排序API - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int[] sortArrayByParitySortAPI(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }

        Arrays.sort(B, Comparator.comparingInt(a -> a % 2));

        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }

        return A;
    }

}
