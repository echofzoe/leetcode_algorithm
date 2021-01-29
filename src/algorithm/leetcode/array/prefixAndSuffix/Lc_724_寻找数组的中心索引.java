package algorithm.leetcode.array.prefixAndSuffix;

import java.util.Arrays;

public class Lc_724_寻找数组的中心索引 {

    // 寻找数组的中心索引
    // https://leetcode-cn.com/problems/find-pivot-index/

    public static void main(String[] args) {
        Lc_724_寻找数组的中心索引 lc = new Lc_724_寻找数组的中心索引();
        int[] nums = {1, 7, 3, 6, 5, 6};

        System.out.println(Arrays.toString(nums) + "的中心索引是 " + lc.pivotIndex(nums));
    }

    // 前缀和 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        int sum = Arrays.stream(nums).sum();

        int preSum = 0;
        for (int i = 0; i < n; i++) {
            if (2 * preSum + nums[i] == sum) {
                return i;
            }

            preSum += nums[i];
        }

        return -1;
    }

}
