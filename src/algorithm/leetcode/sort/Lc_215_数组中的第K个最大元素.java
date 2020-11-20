package algorithm.leetcode.sort;

import java.util.Arrays;

public class Lc_215_数组中的第K个最大元素 {

    // 数组中的第K个最大元素
    // https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

    public static void main(String[] args) {
        Lc_215_数组中的第K个最大元素 lc = new Lc_215_数组中的第K个最大元素();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        
        System.out.println("无序数组" + Arrays.toString(nums) + "中第" + k + "个最大的元素是" + lc.findKthLargestQuicklySort(nums, k));
    }

    // 快速排序 - 时间复杂度 O(N*logN) - 空间复杂度 O(logN)
    public int findKthLargestQuicklySort(int[] nums, int k) {
        
    }

}
