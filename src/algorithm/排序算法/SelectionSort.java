package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序 - 最佳情况：T(n) = O(n^2)   最坏情况：T(n) = O(n^2)   平均情况：T(n) = O(n^2)
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class SelectionSort {

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int tmp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = tmp;
        }

        return arr;
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 21, 5, 3, 6, 7, 56, 8, 1};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }

}
