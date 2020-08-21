package algorithm.leetcode;

public class Lc_26 {

    // 注意：题目要求 “原地”，这意味着不能复制数组

    public static void main(String[] args) {
        Lc_26 lc = new Lc_26();
        int[] nums = {1, 1, 1, 2, 3};

        System.out.println(lc.removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;    // i - 慢指针; j - 快指针
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;    // i 是索引，所以作为长度要 +1
    }
}
