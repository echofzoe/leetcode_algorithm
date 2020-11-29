package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_493_翻转对 {

    // 翻转对
    // https://leetcode-cn.com/problems/reverse-pairs/

    public static void main(String[] args) {
        Lc_493_翻转对 lc = new Lc_493_翻转对();
        int[] input = {2, 4, 3, 5, 1};

        System.out.println("数组" + Arrays.toString(input) + "中的重要翻转对的数量是" + lc.reversePairsMergeSort(input));
    }

    // 暴力 - 时间复杂度O(N^2) - 空间复杂度(1)
    public int reversePairsBF(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] > 2 * (long) nums[j]) {
                    res++;
                }
            }
        }

        return res;
    }

    // 归并排序 - 时间复杂度O(N*logN) - 空间复杂度(N)
    public int reversePairsMergeSort(int[] nums) {
        if (nums.length == 0) return 0;

        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    private int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) return 0;

        int mid = left + (right - left) / 2;
        int n1 = reversePairsRecursive(nums, left, mid);
        int n2 = reversePairsRecursive(nums, mid + 1, right);
        int res = n1 + n2;

        // 首先统计下标对的数量
        int i = left, j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }

            res += j - (mid + 1);
            i++;
        }

        // 随后合并两个排序数组
        int[] sorted = new int[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) {
                sorted[p++] = nums[p2++];
            } else if (p2 > right) {
                sorted[p++] = nums[p1++];
            } else {
                if (nums[p1] < nums[p2]) {
                    sorted[p++] = nums[p1++];
                } else {
                    sorted[p++] = nums[p2++];
                }
            }
        }
        for (int k = 0; k < sorted.length; k++) {
            nums[left + k] = sorted[k];
        }

        return res;
    }

}
