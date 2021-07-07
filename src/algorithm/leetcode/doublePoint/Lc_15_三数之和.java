package algorithm.leetcode.doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * <P>https://leetcode-cn.com/problems/3sum/</P>
 *
 * @author echofzoe
 * @updated 2021.7.7
 * @since unknown
 */
public class Lc_15_三数之和 {

    public static void main(String[] args) {
        Lc_15_三数之和 lc = new Lc_15_三数之和();

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};

        System.out.println("给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。\n" +
                "注意：答案中不可以包含重复的三元组。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.threeSum(nums));  // [[-1,-1,2],[-1,0,1]]
    }

    // 排序 + 双指针 - 时间复杂度 O(N^2) - 空间复杂度 O(logN) 为排序数组所使用的额外空间
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n;
        if (nums == null || (n = nums.length) < 3) return res;

        Arrays.sort(nums);

        // 枚举 a
        for (int k = 0; k < n - 2; k++) {
            int a = nums[k];

            if (a > 0) break;  // 剪枝
            if (k > 0 && a == nums[k - 1]) continue;  // 去重

            // 枚举 b、c
            int i = k + 1, j = n - 1;
            while (i < j) {
                int b = nums[i], c = nums[j];
                int sum = a + b + c;

                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    res.add(Arrays.asList(a, b, c));

                    // 去重，收缩边界
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }

        return res;
    }
}
