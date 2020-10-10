package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_26_删除排序数组中的重复项 {

    // 删除排序数组中的重复项
    // https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/

    // 注意：题目要求 “原地”，这意味着不能复制数组

    public static void main(String[] args) {
        Lc_26_删除排序数组中的重复项 lc = new Lc_26_删除排序数组中的重复项();
        int[] nums = {1, 1, 1, 2, 3};

        System.out.println("数组 " + Arrays.toString(nums) + " 删除重复元素后的长度为 " + lc.removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;    // i - 慢指针; j - 快指针
        for ( ; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;    // i 是索引，所以作为长度要 +1
    }
}
