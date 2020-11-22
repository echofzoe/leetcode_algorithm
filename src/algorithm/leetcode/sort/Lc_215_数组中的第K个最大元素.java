package algorithm.leetcode.sort;

import algorithm.leetcode.utils.NumsUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Lc_215_数组中的第K个最大元素 {

    // 数组中的第K个最大元素
    // https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

    public static void main(String[] args) {
        Lc_215_数组中的第K个最大元素 lc = new Lc_215_数组中的第K个最大元素();
//        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] nums = {3, 2, 1, 5, 6, 4};
//        int k = 4;
        int k = 2;

        System.out.println("无序数组" + Arrays.toString(nums) + "中第" + k + "个最大的元素是" + lc.findKthLargestMyHeap(nums, k));
    }

    // 完全快速排序 + 遍历 - 时间复杂度 O(N*logN) - 空间复杂度 O(logN) 为递归栈的开销
    public int findKthLargestQuicklySort(int[] nums, int k) {
        quicklySort(nums, 0, nums.length - 1);

        return nums[nums.length - k];
    }

    private void quicklySort(int[] nums, int left, int right) {
        if (left >= right) return;

        int i = left, j = right + 1, pivot = nums[left];

        while (true) {
            while (i < right && nums[++i] <= pivot) ;

            while (j > left && nums[--j] >= pivot) ;

            if (i >= j) break;

            NumsUtils.swap(nums, i, j, true);
        }
        NumsUtils.swap(nums, left, j, true);

        quicklySort(nums, left, j - 1);
        quicklySort(nums, j + 1, right);
    }

    // 快速排序优化 在排序过程中比对结果 - 时间复杂度O(N) - 空间复杂度(logN)
    public int findKthLargestQuicklySortOptimization(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int k2 = nums.length - k;    // 第k个最大的数即第k2个最小的数

        while (left < right) {
            int index = quicklySort2(nums, left, right);
            if (index == k2) break;
            else if (index < k2) left = index + 1;
            else right = index - 1;
        }

        return nums[k2];
    }

    private int quicklySort2(int[] nums, int left, int right) {
        int i = left, j = right + 1, pivot = nums[left];

        while (true) {
            while (i < right && nums[++i] <= pivot) ;
            while (j > left && nums[--j] >= pivot) ;
            if (i >= j) break;
            NumsUtils.swap(nums, i, j, true);
        }

        NumsUtils.swap(nums, left, j, true);

        return j;
    }

    // 大根堆（库函数） - 时间复杂度O(N*logN) - 空间复杂度(N)
    public int findKthLargestHeap(int[] nums, int k) {
        Queue<Integer> pg = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            pg.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            pg.poll();
        }

        return pg.poll();
    }

    // 大根堆（自己实现堆） - 时间复杂度O(N*logN) - 空间复杂度(N)
    public int findKthLargestMyHeap(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
