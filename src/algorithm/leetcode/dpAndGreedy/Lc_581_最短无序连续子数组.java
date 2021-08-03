package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_581_最短无序连续子数组 {

    public static void main(String[] args) {
        Lc_581_最短无序连续子数组 lc = new Lc_581_最短无序连续子数组();

        int[] nums = {2,6,4,8,10,9,15};
        
        System.out.println("给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。\n" +
                "请你找出符合题意的 最短 子数组，并输出它的长度。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findUnsortedSubarray(nums));  // 5

    }

    // 贪心 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    public int findUnsortedSubarray(int[] nums) {
        if (isSorted(nums)) return 0;

        int n = nums.length;

        int[] t = new int[n];
        System.arraycopy(nums, 0, t, 0, n);
        Arrays.sort(t);

        int l = 0, r = n - 1;
        while (nums[l] == t[l]) l++;
        while (nums[r] == t[r]) r--;

        return r - l + 1;
    }

    // 一次遍历 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int findUnsortedSubarray1(int[] nums) {
        if (isSorted(nums)) return 0;

        int n = nums.length;

        int min = Integer.MAX_VALUE, l = -1;
        int max = Integer.MIN_VALUE, r = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] < max) r = i;
            else max = nums[i];

            if (min < nums[n - 1 - i]) l = n - 1 - i;
            else min = nums[n - 1 - i];
        }

        return r == -1 ? 0 : r - l + 1;
    }

    private boolean isSorted(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }

        return true;
    }

}
