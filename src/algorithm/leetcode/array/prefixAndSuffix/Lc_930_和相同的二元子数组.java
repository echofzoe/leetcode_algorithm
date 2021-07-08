package algorithm.leetcode.array.prefixAndSuffix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 和相同的二元子数组
 * <P>https://leetcode-cn.com/problems/binary-subarrays-with-sum/</P>
 *
 * @author echofzoe
 * @since 2021.7.8
 */
public class Lc_930_和相同的二元子数组 {

    public static void main(String[] args) {
        Lc_930_和相同的二元子数组 lc = new Lc_930_和相同的二元子数组();

        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        System.out.println("给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。\n" +
                "子数组 是数组的一段连续部分。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", goal = " + goal);
        System.out.println("输出：" + lc.numSubarraysWithSum1(nums, goal));  // 4
    }

    // 前缀和 + 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;

        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);

        int res = 0;
        for (int i = 0; i < n; i++) {
            int r = pre[i + 1], l = r - goal;
            res += m.getOrDefault(l, 0);
            m.put(r, m.getOrDefault(r, 0) + 1);
        }

        return res;
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;

        int res = 0;

        for (int r = 0, l1 = 0, l2 = 0, s1 = 0, s2 = 0; r < n; r++) {
            s1 += nums[r];
            s2 += nums[r];
            while (l1 <= r && s1 > goal) s1 -= nums[l1++];
            while (l2 <= r && s2 >= goal) s2 -= nums[l2++];

            res += l2 - l1;
        }

        return res;
    }

}
