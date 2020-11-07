package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_327_区间和的个数 {

    // 区间和的个数
    // https://leetcode-cn.com/problems/count-of-range-sum/

    public static void main(String[] args) {
        Lc_327_区间和的个数 lc = new Lc_327_区间和的个数();
        int[] nums = {-2147483647, 0, -2147483647, 2147483647};
        int lower = -564;
        int upper = 3864;

        System.out.println("数组" + Arrays.toString(nums) + "中区间和在[" + lower + ", " + upper + "]之间的个数是" + lc.countRangeSumBruteForce(nums, lower, upper));
    }

    // 暴力 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int countRangeSumBruteForce(int[] nums, int lower, int upper) {
        int cnt = 0;
        long sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            for (int j = i; j < nums.length; j++) {
                if (j != i) sum += nums[j];
                if (sum >= lower && sum <= upper) cnt++;
            }
            sum = 0;
        }

        return cnt;
    }

    // 归并排序 - 时间复杂度 O(N*logN) - 空间复杂度 O(N)
    public int countRangeSumMerge(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            sum[i + 1] = s;
        }

        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    private int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = left + (right - left) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }

}
