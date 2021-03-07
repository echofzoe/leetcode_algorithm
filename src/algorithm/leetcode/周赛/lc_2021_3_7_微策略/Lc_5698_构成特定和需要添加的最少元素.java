package algorithm.leetcode.周赛.lc_2021_3_7_微策略;

import java.util.Arrays;

public class Lc_5698_构成特定和需要添加的最少元素 {

    // 构成特定和需要添加的最少元素
    // https://leetcode-cn.com/problems/minimum-elements-to-add-to-form-a-given-sum/

    public static void main(String[] args) {
        Lc_5698_构成特定和需要添加的最少元素 lc = new Lc_5698_构成特定和需要添加的最少元素();
        int[] nums = {1, -10, 9, 1};
        int limit = 100, goal = 0;

        System.out.println("整数数组nums=" + Arrays.toString(nums) + "每次只能添加一个abs(x)<=" + limit + "的元素，则要达到sum(nums)=" + goal + "需要进行最少" + lc.minElements(nums, limit, goal) + "次操作");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (long num : nums) {
            sum += num;
        }
        return (int) ((Math.abs(sum - goal) + limit - 1) / limit);
    }

}
