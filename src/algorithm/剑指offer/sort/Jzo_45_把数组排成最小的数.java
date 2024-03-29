package algorithm.剑指offer.sort;

import java.util.Arrays;

/**
 * 把数组排成最小的数
 * <P>https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/</P>
 *
 * @author echofzoe
 * @since 2021.5.27
 */
public class Jzo_45_把数组排成最小的数 {

    public static void main(String[] args) {
        Jzo_45_把数组排成最小的数 lc = new Jzo_45_把数组排成最小的数();

        int[] nums = {3, 30, 34, 5, 9};

        System.out.println("输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.minNumber1(nums));  // 3033459
    }

    // 调用排序API - 时间复杂度 O(NlogN) 最差为 O(N^2) - 空间复杂度 O(N)
    public String minNumber(int[] nums) {
        int n = nums.length;

        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(ss, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
        }

        return sb.toString();
    }

    // 快速排序 - 时间复杂度 O(NlogN) 最差为 O(N^2) - 空间复杂度 O(N)
    public String minNumber1(int[] nums) {
        int n = nums.length;

        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = String.valueOf(nums[i]);
        }

        quickSort(ss, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
        }

        return sb.toString();
    }

    private void quickSort(String[] ss, int l, int r) {
        if (l >= r) return;

        int i = l, j = r + 1;
        String pivot = ss[l];

        while (true) {
            do {
                ++i;
            } while (i < r && (ss[i] + pivot).compareTo(pivot + ss[i]) <= 0);

            while (j > l && (ss[--j] + pivot).compareTo(pivot + ss[j]) >= 0);

            if (i >= j) break;

            swap(ss, i, j);
        }

        swap(ss, l, j);

        quickSort(ss, l, j - 1);
        quickSort(ss, j + 1, r);
    }

    private void swap(String[] ss, int i, int j) {
        String tmp = ss[i];
        ss[i] = ss[j];
        ss[j] = tmp;
    }

}
