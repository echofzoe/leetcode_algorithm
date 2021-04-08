package algorithm.剑指offer;

import java.util.Arrays;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Jz_39_数组中出现次数超过一半的数字 {

    public static void main(String[] args) {
        Jz_39_数组中出现次数超过一半的数字 jz = new Jz_39_数组中出现次数超过一半的数字();

        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};

        System.out.println(Arrays.toString(nums) + "中出现次数超过一半的数字是" + jz.majorityElement2(nums));
    }

    // 排序 - 时间复杂度 O(logN) - 空间复杂度 O(1)
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 摩尔投票法 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;

            votes += num == x ? 1 : -1;
        }

        return x;
    }

}
