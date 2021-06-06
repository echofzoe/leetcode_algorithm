package algorithm.leetcode.周赛.lc_2021_6_6_恒生;

import java.util.Arrays;

/**
 * 使数组元素相等的减少操作次数
 * <P>https://leetcode-cn.com/problems/reduction-operations-to-make-the-array-elements-equal/</P>
 *
 * @author echofzoe
 * @since 2021.6.6
 */
public class Lc_5777_使数组元素相等的减少操作次数 {

    public static void main(String[] args) {
        Lc_5777_使数组元素相等的减少操作次数 lc = new Lc_5777_使数组元素相等的减少操作次数();

        int[] nums = {1, 1, 2, 2, 3};

        System.out.println("给你一个整数数组 nums ，你的目标是令 nums 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：\n" +
                "  - 找出 nums 中的 最大 值。记这个值为 largest 并取其下标 i （下标从 0 开始计数）。如果有多个元素都是最大值，则取最小的 i 。\n" +
                "  - 找出 nums 中的 下一个最大 值，这个值 严格小于 largest ，记为 nextLargest 。\n" +
                "  - 将 nums[i] 减少到 nextLargest 。\n" +
                "返回使 nums 中的所有元素相等的操作次数。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.reductionOperations(nums));
    }

    // 模拟 - 时间复杂度 O(NlogN) 为排序时间复杂度 - 空间复杂度 O(logN) 为排序时栈的开销
    public int reductionOperations(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        Arrays.sort(nums);

        int res = 0, max = nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            int cur = nums[i];
            if (cur < max) {
                res += n - 1 - i;
                max = cur;
            }
        }

        return res;
    }

}
