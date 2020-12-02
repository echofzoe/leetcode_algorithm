package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

public class Lc_153_寻找旋转排序数组中的最小值 {

    // 寻找旋转排序数组中的最小值
    // https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

    public static void main(String[] args) {
        Lc_153_寻找旋转排序数组中的最小值 lc = new Lc_153_寻找旋转排序数组中的最小值();
        int[] input = {3, 4, 5, 1, 2};

        System.out.println("旋转升序数组" + Arrays.toString(input) + "中，最小值为" + lc.findMin(input));
    }

    // 二分查找 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int findMin(int[] nums) {
        int left = 0, mid, right = nums.length - 1;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
        }

        return nums[left];
    }

}
