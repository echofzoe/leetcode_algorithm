package algorithm.排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * 希尔排序 - 最佳情况：T(N) = O(NlogN)  最坏情况：T(N) = O(NlogN)  平均情况：T(N) = O(NlogN)
 *
 * @author echofzoe
 * @since 2021.4.2
 */
public class ShellSort {

    public static int[] shellSort(int[] arr) {
        int len = arr.length;

        int gap = len / 2;  // 初始化增量
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int cur = arr[i], preIdx = i - gap;

                while (preIdx >= 0 && arr[preIdx] > cur) {
                    arr[preIdx + gap] = arr[preIdx];
                    preIdx -= gap;
                }

                arr[preIdx + gap] = cur;
            }

            gap /= 2;
        }

        return arr;
    }


    @Test
    public void test() {
        int[] arr = new int[]{3, 4, 21, 5, 3, 6, 7, 56, 8, 1};
        System.out.println(Arrays.toString(shellSort(arr)));
    }
}
