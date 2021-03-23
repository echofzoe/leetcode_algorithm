package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

public class ShellSort {

    /**
     * 希尔排序
     * 最佳情况：T(n) = O(nlgn)  最坏情况：T(n) = O(nlgn)  平均情况：T(n) =O(nlgn)　
     */
    public static int[] shellSort(int[] arr) {
        int len = arr.length;

        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int cur = arr[i], preIndex = i - gap;

                while (preIndex >= 0 && arr[preIndex] > cur) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }

                arr[preIndex + gap] = cur;
            }
            gap /= 2;
        }

        return arr;
    }


    @Test
    public void test() {
        int[] arr = new int[] {3,4,21,5,3,6,7,56,8,1};
        System.out.println(Arrays.toString(shellSort(arr)));
    }
}
