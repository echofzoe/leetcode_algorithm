package algorithm.leetcode.utils;

public class NumsUtils {

    public static void swap(int[] nums, int i, int j, boolean numsContainsRepeat) {
        if (numsContainsRepeat) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        } else {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    public static <T extends Number> void swap(T[] nums, int i, int j) {
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
