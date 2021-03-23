package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

public class InsertionSort {

    /**
     * 插入排序
     *
     */
    public static int[] insertionSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            int cur = arr[i + 1], preIndex = i;

            while (preIndex >= 0 && arr[preIndex] > cur) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }

            arr[preIndex + 1] = cur;
        }

        return arr;
    }

    @Test
    public void test() {
        int[] arr = new int[] {3,4,21,5,3,6,7,56,8,1};
        System.out.println(Arrays.toString(insertionSort(arr)));
    }

}
