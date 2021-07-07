package algorithm.leetcode.doublePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * <P>https://leetcode-cn.com/problems/4sum/</P>
 *
 * @author echofzoe
 * @updated 2021.7.7
 * @since unknown
 */
public class Lc_18_四数之和 {

    public static void main(String[] args) {
        Lc_18_四数之和 lc = new Lc_18_四数之和();

        int[] nums = {1, -2, -5, -4, -3, 3, 3, 5};
        int target = -11;

        System.out.println("输入：nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("输出：" + lc.fourSum(nums, target));  // [[-5, -4, -3, 1]]
    }

    // 排序 + 双指针 - 时间复杂度 O(N^3) - 空间复杂度 O(logN) 为排序所用额外空间
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n;
        if (nums == null || (n = nums.length) < 4) return res;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            int a = nums[i];

            for (int j = i + 1; j < n - 2; j++) {
                // 剪枝
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if (a + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (a + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int b = nums[j];
                // if (a + b > target) break;  // 有负数的情况下要去掉这句

                int l = j + 1, r = n - 1;
                while (l < r) {
                    int c = nums[l], d = nums[r];
                    int sum = a + b + c + d;

                    if (sum < target) {
                        while (l < r && nums[l] == nums[++l]) ;
                    } else if (sum > target) {
                        while (l < r && nums[r] == nums[--r]) ;
                    } else {
                        res.add(Arrays.asList(a, b, c, d));

                        while (l < r && nums[l] == nums[++l]) ;
                        while (l < r && nums[r] == nums[--r]) ;
                    }
                }
            }
        }

        return res;
    }
}
