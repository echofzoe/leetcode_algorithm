package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

public class Lc_154_寻找旋转排序数组中的最小值II {

    // 寻找旋转排序数组中的最小值 II
    // https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/

    public static void main(String[] args) {
        Lc_154_寻找旋转排序数组中的最小值II lc = new Lc_154_寻找旋转排序数组中的最小值II();
        int[] input = {3, 3, 1, 3};

        System.out.println("包含重复元素的旋转升序数组" + Arrays.toString(input) + "中，最小值为" + lc.findMin(input));
    }

    // 二分查找 - 时间复杂度 平均 O(logN) 最坏 O(N) 最坏时数组中元素全部相同，while循环执行N次 - 空间复杂度 O(1)
    public int findMin(int[] nums) {
        int left = 0, mid, right = nums.length - 1;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] == nums[right]) {
                right--;
            }
        }

        return nums[left];
    }

}
