package algorithm.leetcode.array;

import java.util.Arrays;

/**
 * 主要元素
 * <P>https://leetcode-cn.com/problems/find-majority-element-lcci/</P>
 *
 * @author echofzoe
 * @since 2021.7.9
 */
public class Lc_面试题_17_10_主要元素 {

    public static void main(String[] args) {
        Lc_面试题_17_10_主要元素 lc = new Lc_面试题_17_10_主要元素();

        int[] nums = {1, 2, 5, 9, 5, 9, 5, 5, 5};

        System.out.println("数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1。\n" +
                "请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.majorityElement(nums));  // 5
    }

    // Boyer-Moore 投票算法 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int majorityElement(int[] nums) {
        int n = nums.length;

        int cand = -1, cnt = 0;
        for (int x : nums) {
            if (cnt == 0) cand = x;

            if (cand == x) cnt++;
            else cnt--;
        }

        cnt = 0;
        for (int x : nums) {
            if (x == cand) cnt++;
        }

        return cnt > n / 2 ? cand : -1;
    }

}
