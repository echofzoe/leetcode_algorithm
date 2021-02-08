package algorithm.leetcode.sort;

import algorithm.leetcode.utils.NumsUtils;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Jzo_40_最小的k个数 {

    // 最小的k个数
    // https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/

    public static void main(String[] args) {
        Jzo_40_最小的k个数 lc = new Jzo_40_最小的k个数();
        int[] arr = {0, 1, 2, 1};
        int k = 1;

        System.out.println(Arrays.toString(arr) + "中最小的" + k + "个数是" + Arrays.toString(lc.getLeastNumbersQuickSort(arr, k)));
    }

    // 快排 - 时间复杂度 O(N) 最坏情况下O(N^2) - 空间复杂度 O(1)
    public int[] getLeastNumbersQuickSort(int[] arr, int k) {
        int n = arr.length;
        if (k == 0) return new int[0];
        else if (k >= n) return arr;

        int left = 0, right = n - 1;
        while (left < right) {
            int idx = quickSort(arr, left, right);

            if (idx == k) break;
            else if (idx < k) left = idx + 1;
            else right = idx - 1;
        }

        return Arrays.copyOf(arr, k);
    }

    private int quickSort(int[] arr, int left, int right) {
        int lo = left, hi = right + 1, pivot = arr[left];

        while (true) {
            while (lo < right && arr[++lo] <= pivot) ;
            while (hi > left && arr[--hi] >= pivot) ;

            if (lo >= hi) break;

            NumsUtils.swap(arr, lo, hi);
        }

        NumsUtils.swap(arr, left, hi);

        return hi;
    }

    // 大根堆 - 时间复杂度 O(NlogK) - 空间复杂度 O(K)
    public int[] getLeastNumbersHeap(int[] arr, int k) {
        int n = arr.length;
        if (k >= n) return arr;

        Queue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i : arr) {
            // 入堆
            if (pq.isEmpty() || pq.size() < k || i < pq.peek()) pq.offer(i);
            // 出堆
            if (pq.size() > k) pq.poll();
        }

        int[] res = new int[k];
        int idx = 0;
        for (int i : pq) res[idx++] = i;

        return res;
    }

}
