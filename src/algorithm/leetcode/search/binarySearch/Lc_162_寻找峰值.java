package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * 寻找峰值
 * <P>https://leetcode-cn.com/problems/find-peak-element/</P>
 *
 * @author echofzoe
 * @since 2021.8.3
 */
public class Lc_162_寻找峰值 {

    public static void main(String[] args) {
        Lc_162_寻找峰值 lc = new Lc_162_寻找峰值();

        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        
        System.out.println("峰值元素是指其值大于左右相邻值的元素。\n" +
                "给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。\n" +
                "你可以假设 nums[-1] = nums[n] = -∞ 。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findPeakElement(nums));  // 1 或 5
    }

    // 二分查找 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        int l = 0, m, r = n - 1;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] < nums[m + 1]) l = m + 1;
            else r = m;
        }

        return l;
    }

    // 二分查找 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int findPeakElement1(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }

        return n - 1;
    }

}
