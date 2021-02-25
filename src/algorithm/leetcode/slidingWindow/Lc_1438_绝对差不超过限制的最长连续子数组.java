package algorithm.leetcode.slidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class Lc_1438_绝对差不超过限制的最长连续子数组 {

    // 绝对差不超过限制的最长连续子数组
    // https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

    public static void main(String[] args) {
        Lc_1438_绝对差不超过限制的最长连续子数组 lc = new Lc_1438_绝对差不超过限制的最长连续子数组();
        int[] nums = {10, 1, 2, 4, 7, 2};
        int limit = 5;

        System.out.println(Arrays.toString(nums) + "中满足子数组中最大绝对差不大于" + limit + "的最长子数组的长度是" + lc.longestSubarray2(nums, limit));
    }

    // 滑动窗口 + 单调队列 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int longestSubarray1(int[] nums, int limit) {
        int n = nums.length;

        Deque<Integer> minQue = new LinkedList<>();
        Deque<Integer> maxQue = new LinkedList<>();

        int lo = 0, hi = 0, res = 0;
        while (hi < n) {
            while (!minQue.isEmpty() && minQue.peekLast() > nums[hi]) minQue.pollLast();
            while (!maxQue.isEmpty() && maxQue.peekLast() < nums[hi]) maxQue.pollLast();

            minQue.offerLast(nums[hi]);
            maxQue.offerLast(nums[hi]);

            while (!minQue.isEmpty() && !maxQue.isEmpty() && maxQue.peekFirst() - minQue.peekFirst() > limit) {
                if (nums[lo] == minQue.peekFirst()) {
                    minQue.pollFirst();
                }

                if (nums[lo] == maxQue.peekFirst()) {
                    maxQue.pollFirst();
                }

                lo++;
            }

            res = Math.max(res, hi - lo + 1);
            hi++;
        }

        return res;
    }

    // 滑动窗口 + 有序集合 - 时间复杂度 O(N*logN) - 空间复杂度 O(N)
    public int longestSubarray2(int[] nums, int limit) {
        int n = nums.length;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        int lo = 0, hi = 0, res = 0;
        while (hi < n) {
            map.put(nums[hi], map.getOrDefault(nums[hi], 0) + 1);

            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[lo], map.get(nums[lo]) - 1);
                if (map.get(nums[lo]) == 0) {
                    map.remove(nums[lo]);
                }

                lo++;
            }

            res = Math.max(res, hi - lo + 1);
            hi++;
        }

        return res;
    }

}
