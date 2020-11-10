package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_31_下一个排列 {

    // 下一个排列
    // https://leetcode-cn.com/problems/next-permutation/

    public static void main(String[] args) {
        Lc_31_下一个排列 lc = new Lc_31_下一个排列();
        int[] input = {4, 5, 2, 6, 3, 1};

        System.out.print("排列" + Arrays.toString(input) + "的下一个更大的排列（如果不存在下一个更大的排列，则将数字重新排列成最小的排列，即升序排列）是");
        lc.nextPermutation(input);
        System.out.println(Arrays.toString(input));
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[j] ^ nums[i];
        nums[i] = nums[i] ^ nums[j];
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
