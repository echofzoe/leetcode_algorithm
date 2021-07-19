package algorithm.leetcode.slidingWindow;

import java.util.Arrays;

/**
 * 最高频元素的频数
 * <P>https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/</P>
 *
 * @author echofzoe
 * @since 2021.7.19
 */
public class Lc_1838_最高频元素的频数 {

    public static void main(String[] args) {
        Lc_1838_最高频元素的频数 lc = new Lc_1838_最高频元素的频数();

        int[] nums = {1, 4, 8, 13};
        int k = 5;

        System.out.println("元素的 频数 是该元素在一个数组中出现的次数。\n" +
                "给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。\n" +
                "执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("输出：" + lc.maxFrequency(nums, k));  // 2
    }

    // 滑动窗口 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN) 为排序所使用的额外空间
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ope = 0, res = 1;
        int l = 0, r = 1;
        while (r < n) {
            ope += (nums[r] - nums[r - 1]) * (r - l);
            while (ope > k) {
                ope -= nums[r] - nums[l++];
            }

            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }

}
