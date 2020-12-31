package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_330_按要求补齐数组 {

    // 按要求补齐数组
    // https://leetcode-cn.com/problems/patching-array/

    public static void main(String[] args) {
        Lc_330_按要求补齐数组 lc = new Lc_330_按要求补齐数组();
        int[] input = {1, 5, 10};
        int n = 20;

        System.out.println("给定一个已排序的正整数数组 nums = " + Arrays.toString(input) + "，最少需要补充的" + lc.minPatches(input, n) + "个数字，才能使得[1, " + n + "]区间内的任何数字都可以用nums中某几个数字的和来表示");
    }

    // 贪心 - 时间复杂度 O(m+logN) - 空间复杂度 O(1)
    public int minPatches(int[] nums, int n) {
        int len = nums.length;

        long x = 1;
        int res = 0, index = 0;
        while (x <= n) {
            if (index < len && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                res++;
            }
        }

        return res;
    }

}
