package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序 - 最佳情况：T(N) = O(NlogN)   最差情况：T(N) = O(N^2)   平均情况：T(N) = O(NlogN)
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class QuickSort {

    public static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo, j = hi + 1, pivot = arr[lo];

        while (true) {
            while (i < hi && arr[++i] <= pivot) ;
            while (j > lo && arr[--j] >= pivot) ;

            if (i >= j) break;

            swap(arr, i, j);
        }
        swap(arr, lo, j);

        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 21, 5, 3, 6, 7, 56, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
