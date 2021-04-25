package algorithm.leetcode.周赛.lc_2021_4_25_发发奇;

import java.util.Arrays;

/**
 * 最高频元素的频数
 * <P>https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/</P>
 *
 * @author echofzoe
 * @since 2021.4.25
 */
public class Lc_5739_最高频元素的频数 {

    public static void main(String[] args) {
        Lc_5739_最高频元素的频数 lc = new Lc_5739_最高频元素的频数();

        int[] nums = {1, 4, 8, 13};
        int k = 5;

        System.out.println("给你一个整数数组 " + Arrays.toString(nums) + " 和一个整数 " + k + " 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1");
        System.out.println("执行最多 " + k + " 次操作后，数组中最高频元素的 最大可能频数 是 " + lc.maxFrequency2(nums, k));  // 2
    }

    // 滑动窗口 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxFrequency1(int[] nums, int k) {
        int n = nums.length;

        int res = 0, lo = 0, hi = 0, sum = 0;
        while (hi < n) {
            sum += nums[hi];

            while (sum + k < nums[hi] * (hi - lo + 1)) {
                sum -= nums[lo];
                lo++;
            }

            res = Math.max(res, hi - lo + 1);
            hi++;
        }

        return res;
    }

    // 前缀和 + 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int maxFrequency2(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int res = 1;
        for (int i = 1; i <= n; i++) {
            int lo = 1, mid, hi = i;
            while (lo < hi) {
                mid = lo + (hi - lo) / 2;

                if (k + preSum[i] - preSum[mid - 1] < (long) nums[i - 1] * (i - mid + 1)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            res = Math.max(res, i - lo + 1);
        }

        return res;
    }

}
