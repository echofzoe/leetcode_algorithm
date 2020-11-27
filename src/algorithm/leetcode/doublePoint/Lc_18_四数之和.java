package algorithm.leetcode.doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc_18_四数之和 {

    // 四数之和
    // https://leetcode-cn.com/problems/4sum/

    public static void main(String[] args) {
        Lc_18_四数之和 lc = new Lc_18_四数之和();
        int[] input = {1, 0, -1, 0, -2, 2};
        int target = 0;

        System.out.println("数组" + Arrays.toString(input) + "中四数之和等于" + target + "的不包含重复的四元组集合为 " + Arrays.deepToString(lc.fourSum(input, target).toArray()));
    }

    // 排序 + 双指针 - 时间复杂度 O(N^3) - 空间复杂度 O(logN) 为排序所用额外空间
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);
        int length = nums.length;
        for (int a = 0; a < length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            for (int b = a + 1; b < length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;
                int c = b + 1, d = length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum > target) {
                        while (c < d && nums[d] == nums[--d]) ;
                    } else if (sum < target) {
                        while (c < d && nums[c] == nums[++c]) ;
                    } else {
                        res.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[++c]) ;
                        while (c < d && nums[d] == nums[--d]) ;
                    }
                }
            }
        }

        return res;
    }
}
