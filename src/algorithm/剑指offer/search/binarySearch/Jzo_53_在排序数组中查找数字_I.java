package algorithm.剑指offer.search.binarySearch;

import java.util.Arrays;

/**
 * 在排序数组中查找数字 I
 * <P>https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/</P>
 *
 * @author echofzoe
 * @since 2021.7.16
 */
public class Jzo_53_在排序数组中查找数字_I {

    public static void main(String[] args) {
        Jzo_53_在排序数组中查找数字_I jz = new Jzo_53_在排序数组中查找数字_I();

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        System.out.println("统计一个数字在排序数组中出现的次数。");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("输出：" + jz.search(nums, target));
    }

    // 二分查target的起始位置 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int search(int[] nums, int target) {
        int n = nums.length;

        int l = 0, m, r = n - 1;
        while (l < r) {
            m = l + (r - l) / 2;

            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                r = m;
            }
        }

        int res = 0;
        for (int i = l; i < n; i++) {
            if (nums[i] == target) res++;
            else break;
        }

        return res;
    }

}
