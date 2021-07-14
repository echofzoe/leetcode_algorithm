package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

/**
 * 绝对差值和
 * <P>https://leetcode-cn.com/problems/minimum-absolute-sum-difference/</P>
 *
 * @author echofzoe
 * @since 2021.7.14
 */
public class Lc_1818_绝对差值和 {

    public static void main(String[] args) {
        Lc_1818_绝对差值和 lc = new Lc_1818_绝对差值和();

        int[] nums1 = {1, 7, 5}, nums2 = {2, 3, 5};

        System.out.println("给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。\n" +
                "数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。\n" +
                "你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。\n" +
                "在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。\n" +
                "|x| 定义为：\n" +
                "  - 如果 x >= 0 ，值为 x ，或者\n" +
                "  - 如果 x <= 0 ，值为 -x\n");
        System.out.println("输入：nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("输出：" + lc.minAbsoluteSumDiff(nums1, nums2));  // 3
    }

    // 排序 + 二分 - 时间复杂度 O(NlogN) 排序占O(NlogN)，一次二分占O(logN)，共二分N次 - 空间复杂度 O(N) 排序占O(logN)，辅助数组占O(N)
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        final int MOD = 1000000007;

        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);

        int sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;

            // 二分查找第一个大于等于nums2[i]的元素
            int j = binarySearch(rec, nums2[i]);

            if (j < n) max = Math.max(max, diff - (rec[j] - nums2[i]));
            // 因为是取差值的绝对值，所以还要比较一下第一个小于nums2[i]的元素
            if (j > 0) max = Math.max(max, diff - (nums2[i] - rec[j - 1]));
        }

        return (sum - max + MOD) % MOD;
    }

    private int binarySearch(int[] arr, int t) {
        int l = 0, m, r = arr.length - 1;
        if (arr[r] < t) return r + 1;

        while (l < r) {
            m = l + (r - l) / 2;
            if (arr[m] < t) l = m + 1;
            else r = m;
        }

        return l;
    }

}
