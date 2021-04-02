package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序 - 最佳情况：T(N) = O(N)   最坏情况：T(N) = O(N^2)   平均情况：T(N) = O(N^2)
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class InsertionSort {

    public static int[] insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int cur = arr[i + 1], preIdx = i;

            while (preIdx >= 0 && arr[preIdx] > cur) {
                arr[preIdx + 1] = arr[preIdx];
                preIdx--;
            }

            arr[preIdx + 1] = cur;
        }

        return arr;
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 21, 5, 3, 6, 7, 56, 8, 1};
        System.out.println(Arrays.toString(insertionSort(arr)));
    }

}
