package algorithm.leetcode.周赛.lc_2021_3_14_shopee;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Lc_5704_好子数组的最大分数 {

    // 好子数组的最大分数
    // https://leetcode-cn.com/problems/maximum-score-of-a-good-subarray/

    public static void main(String[] args) {
        Lc_5704_好子数组的最大分数 lc = new Lc_5704_好子数组的最大分数();
        int[] nums = {1,4,3,7,4,5};
        int k = 3;

        System.out.println("满足题意的" + Arrays.toString(nums) + "中的好子数组的最大分数是" + lc.maximumScore(nums, k));
    }

    public int maximumScore(int[] nums, int k) {
        int n = nums.length;

        int[] left = new int[n], right = new int[n];
        // 双单调栈（单调增）
        Deque<Integer> hi2lo = new ArrayDeque<>(), lo2hi = new ArrayDeque<>();

        // 初始化
        for (int i = 0; i < n; i++) {
            // 找左边最近的比 nums[i] 小的
            while (!hi2lo.isEmpty() && nums[hi2lo.peekLast()] >= nums[i]) hi2lo.pollLast();
            left[i] = hi2lo.isEmpty() ? -1 : hi2lo.peekLast();
            hi2lo.offerLast(i);

            // 找右边最近的比 nums[i] 小的
            while (!lo2hi.isEmpty() && nums[lo2hi.peekLast()] >= nums[n - 1 - i]) lo2hi.pollLast();
            right[n - 1 - i] = lo2hi.isEmpty() ? n : lo2hi.peekLast();
            lo2hi.offerLast(n - 1 - i);
        }

        System.out.println("left: " + Arrays.toString(left));
        System.out.println("right: " + Arrays.toString(right));

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (left[i] + 1 <= k && right[i] - 1 >= k) {
//                res = Math.max(res, ((right[i] - 1) - (left[i] + 1) - 1) * nums[i]);
                res = Math.max(res, (right[i] - left[i] - 1) * nums[i]);
            }
        }

        return res;
    }

}
