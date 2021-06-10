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

    /**
     * 朴素的快速排序算法
     * <P>每次采用待排序数组的排序区间左边界作为 pivot</P>
     * <P>缺点是在数组有序的情况下，时间复杂度会退化到 O(N^2)</P>
     *
     * @param arr 待排序数组
     * @param lo 待排序数组的 排序区间左边界
     * @param hi 待排序数组的 排序区间右边界
     */
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

        // 一趟排序走下来，会保证pivot被放置在了正确的位置，也就是j所在的位置
        // 故，之后需要以j为界限递归划分其左右部分（不用再划分j的位置）
        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }

    /**
     * 双向划分快速排序算法
     * <P>每次采用排序区间中间元素(本算法采用的策略)或随机元素作为 pivot</P>
     * <P>在数组已经有序的情况下，可避免时间复杂度退化到 O(N^2)</P>
     * <P>由于划分完成后pivot不一定在这两部分的分界线上，所以在做比如得到第k大的数这种题目时不能用j-l+1==k来判断q[j]为第k大的数，因为左半区间只保证了所有数小于等于pivot，而不一定都小于等于q[j]</P>
     *
     * @param arr 待排序数组
     * @param lo 待排序数组的 排序区间左边界
     * @param hi 待排序数组的 排序区间右边界
     */
    public static void quickSort1(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo - 1, j = hi + 1, pivot = arr[(lo + hi) >> 1];

        while (true) {
            while (i < hi && arr[++i] < pivot) ;
            while (j > lo && arr[--j] > pivot) ;

            if (i >= j) break;

            swap(arr, i, j);
        }

        // 一趟排序走下来，会保证pivot被放置在了正确的位置，也就是j所在的位置
        // 故，之后需要以j为界限递归划分其左右部分，由于划分的位置含有等于
        quickSort(arr, lo, j);
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
        quickSort1(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
