package algorithm.leetcode.周赛.lc_2021_2_28_蓝湖;

import java.util.Arrays;

public class Lc_5691_通过最少操作次数使数组的和相等 {

    // 通过最少操作次数使数组的和相等
    // https://leetcode-cn.com/problems/equal-sum-arrays-with-minimum-number-of-operations/

    public static void main(String[] args) {
        Lc_5691_通过最少操作次数使数组的和相等 lc = new Lc_5691_通过最少操作次数使数组的和相等();
        int[] nums1 = {1, 2, 3, 4, 5, 6}, nums2 = {1, 1, 2, 2, 2, 2};

        System.out.println("数组" + Arrays.toString(nums1) + "和" + Arrays.toString(nums2) + "中的所有元素取值都在[1,6]之间，则使得sum(nums1)和sum(num2)相等的最少操作次数（若无法使之相等，返回-1）是" + lc.minOperations(nums1, nums2));
    }

    // 贪心 + 双指针 - 时间复杂度 O(NlogN) - 空间复杂度 O(1)
    public int minOperations(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 * 6 < n2 || n1 > n2 * 6) return -1;

        int sum1 = Arrays.stream(nums1).sum(), sum2 = Arrays.stream(nums2).sum();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        if (sum1 > sum2) {
            n1 ^= n2;
            n2 ^= n1;
            n1 ^= n2;

            sum1 ^= sum2;
            sum2 ^= sum1;
            sum1 ^= sum2;

            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int res = 0, idx1 = 0, idx2 = n2 - 1;
        // 每次贪心的取能让sum1和sum2更接近的改变
        while (idx1 < n1 && 0 <= idx2 && sum1 < sum2) {
            if (6 - nums1[idx1] < nums2[idx2] - 1) {
                sum2 = sum2 - nums2[idx2--] + 1;
            } else {
                sum1 = sum1 - nums1[idx1++] + 6;
            }

            res++;
        }

        while (idx1 < n1 && sum1 < sum2) {
            sum1 = sum1 - nums1[idx1++] + 6;
            res++;
        }

        while (0 <= idx2 && sum1 < sum2) {
            sum2 = sum2 - nums2[idx2--] + 1;
            res++;
        }

        return res;
    }

}
