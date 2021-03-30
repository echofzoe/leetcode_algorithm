package interview.笔试题;

import java.util.Arrays;

public class 字节跳动_木棍切割 {

    // 木棍切割
    // - 给定长度为n的数组，每个元素代表一个木头的长度，木头可以任意截断，从这堆木头中截出至少k个相同长度为m的木块。已知k，求max(m)。
    // - 实例：
    // -- 输入 int k = 5, int[] nums = {4,7,2,10,5}
    // -- 输出 4
    // - 解释：
    // -- 最多可以把它分成5段长度为4的木头，即{4,4+3,2,4+4+2,4+1}

    public static void main(String[] args) {
        字节跳动_木棍切割 iv = new 字节跳动_木棍切割();
        int[] nums = {4, 7, 2, 10, 5};
        int k = 5;

        System.out.println("要在木棍堆" + Arrays.toString(nums) + "中截出至少" + k + "个相同长度为m的木块，则m的最大值是" + iv.cutWoodenStickBinarySearch(nums, k));
    }

    // BF - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int cutWoodenStickBF(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);
        int maxM = nums[n - 1], res = -1;
        for (int m = 1; m <= maxM; m++) {
            int cnt = 0;

            for (int num : nums) {
                if (num >= m) {
                    cnt += num / m;
                }
            }

            if (cnt >= k) res = Math.max(res, m);
        }

        return res;
    }

    // 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(1)
    public int cutWoodenStickBinarySearch(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int left = 1, right = nums[n - 1];
        while (left < right) {
            int cnt = 0, mid = left + (right - left) / 2;

            for (int num : nums) {
                if (num >= mid) {
                    cnt += num / mid;
                }
            }

            if (cnt < k) right = mid - 1;
            else if (cnt == k) break;
            else left = mid + 1;
        }

        return left;
    }

}
