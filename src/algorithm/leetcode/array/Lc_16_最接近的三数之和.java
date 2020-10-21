package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_16_最接近的三数之和 {

    // 最接近的三数之和
    // https://leetcode-cn.com/problems/3sum-closest/

    public static void main(String[] args) {
        Lc_16_最接近的三数之和 lc = new Lc_16_最接近的三数之和();
        int[] nums = {-3, -2, -5, 3, -4};
        int target = -1;

        System.out.println("数组 " + Arrays.toString(nums) + " 中与 " + target + " 最接近的三数之和是 " + lc.threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {

//        int res = Integer.MAX_VALUE;    // 负数运算时可能溢出
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);

        for (int k = 0; k < nums.length - 2; k++) {

            int i = k + 1, j = nums.length - 1;

            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    res = Math.abs(target - sum) > Math.abs(target - res) ? res : sum;
                    while (i < j && nums[j] == nums[--j]) ;
                } else if (sum < target) {
                    res = Math.abs(target - sum) > Math.abs(target - res) ? res : sum;
                    while (i < j && nums[i] == nums[++i]) ;
                }
            }
        }

        return res;
    }
}
