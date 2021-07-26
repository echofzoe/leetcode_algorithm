package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序 - 最佳情况：T(N) = O(N)   最坏情况：T(N) = O(N^2)   平均情况：T(N) = O(N^2)
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class BubbleSort {

    public int[] bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        return arr;
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 21, 5, 3, 6, 7, 56, 8, 1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

}
