package algorithm.leetcode.array;

public class Lc_27_移除元素 {

    // 移除元素
    // https://leetcode-cn.com/problems/remove-element/

    // 注意：题目要求 “原地”，这意味着不能复制数组

    public static void main(String[] args) {
        Lc_27_移除元素 lc = new Lc_27_移除元素();
        int[] nums = {4, 1, 2, 3, 5};
        int val = 4;

        System.out.println(lc.removeElement_1(nums, 1));
        System.out.println(lc.removeElement_2(nums, 4));
    }

    // 快慢双指针 - 完全遍历
    private int removeElement_1(int[] nums, int val) {
        if (nums.length == 0) return 0;

        int i = 0;    // i - 慢指针; j - 快指针
        for (int j = 0; j < nums.length; j++) {
            // nums[j] == val 时直接跳过
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

    // 快慢双指针 - 优化遍历
    // 当遇到 nums[i] = val 时，将当前元素与最后一个元素进行交换，并释放最后一个元素，实际上使数组的大小减 1
    private int removeElement_2(int[] nums, int val) {
        if (nums.length == 0) return 0;

        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];    // 交换当前元素与最后一个元素
                n--;    // 数组大小 - 1, 相当于删除重复元素
            } else {
                i++;
            }
        }

        return i;
    }

}
