package algorithm.leetcode.doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_15_三数之和 {

    // 三数之和
    // https://leetcode-cn.com/problems/3sum/

    public static void main(String[] args) {
        Lc_15_三数之和 lc = new Lc_15_三数之和();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println("数组 " + Arrays.toString(nums) + " 中三数之和等于0的三元组有 " + Arrays.deepToString(new List[]{lc.threeSum(nums)}));

    }

    // 排序 + 双指针 - 时间复杂度 O(N^2) - 空间复杂度 O(logN) 为排序数组所使用的额外空间
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) return null;

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int k = 0; k < n - 2; k++) {
            if (nums[k] > 0) break;

            if (k > 0 && nums[k] == nums[k - 1]) continue;

            int i = k + 1, j = nums.length - 1;

            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j])));

                    // 去重,收缩边界
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }

        return res;
    }
}
