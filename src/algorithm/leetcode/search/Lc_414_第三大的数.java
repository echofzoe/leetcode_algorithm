package algorithm.leetcode.search;

import java.util.*;

public class Lc_414_第三大的数 {

    // 第三大的数
    // https://leetcode-cn.com/problems/third-maximum-number/

    public static void main(String[] args) {
        Lc_414_第三大的数 lc = new Lc_414_第三大的数();
        int[] input = {5, 2, 4, 1, 3, 6, 0};

        System.out.println("数组" + Arrays.toString(input) + "中第三大的数是" + lc.thirdMaxHeap(input));
    }

    // BF - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int thirdMaxBF(int[] nums) {
        long[] res = new long[3];
        Arrays.fill(res, Long.MIN_VALUE);

        for (int num : nums) {
            if (num > res[0]) {
                res[2] = res[1];
                res[1] = res[0];
                res[0] = num;
            } else if (num < res[0] && num > res[1]) {
                res[2] = res[1];
                res[1] = num;
            } else if (num < res[1] && num > res[2]) {
                res[2] = num;
            }
        }

        return res[2] == Long.MIN_VALUE ? (int) res[0] : (int) res[2];
    }

    // 小根堆 + Set - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int thirdMaxHeap(int[] nums) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            set.add(num);
            heap.offer(num);
            if (heap.size() > 3) {
                heap.poll();
            }
        }

        if (heap.size() == 3) return heap.peek();
        int max = 0;
        while (!heap.isEmpty()) {
            max = heap.poll();
        }

        return max;
    }
}
