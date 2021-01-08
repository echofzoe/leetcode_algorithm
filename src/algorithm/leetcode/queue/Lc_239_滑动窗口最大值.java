package algorithm.leetcode.queue;

import java.util.*;

public class Lc_239_滑动窗口最大值 {

    // 滑动窗口最大值
    // https://leetcode-cn.com/problems/sliding-window-maximum/

    public static void main(String[] args) {
        Lc_239_滑动窗口最大值 lc = new Lc_239_滑动窗口最大值();
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = {2, 1, 2, 2, 1, 1, 1, 1};
        int k = 3;

        System.out.println("在数组" + Arrays.toString(nums) + "中移动大小为" + k + "的滑动窗口，滑动窗口的最大值是" + Arrays.toString(lc.maxSlidingWindow(nums, k)));
    }

    // 大根堆 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        int[] res = new int[n - k + 1];
        // base case
        res[0] = pq.peek()[0];

        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] < i - k + 1) {
                pq.poll();
            }

            res[i - k + 1] = pq.peek()[0];
        }

        return res;
    }

    // 单调队列 - 时间复杂度 O(N) - 空间复杂度 O(K)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new LinkedList<>();    // 单调递减
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] res = new int[n - k + 1];
        res[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            while (deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            res[i - k + 1] = nums[deque.peekFirst()];
        }

        return res;
    }

}
