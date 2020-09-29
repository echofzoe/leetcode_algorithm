package algorithm.leetcode.array;

import java.util.*;

public class Jzo_3_数组中重复的数字 {

    // 数组中重复的数字
    // https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/

    public static void main(String[] args) {
        Jzo_3_数组中重复的数字 lc = new Jzo_3_数组中重复的数字();
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        
        System.out.println("数组 " + Arrays.toString(nums) + " 中的重复数字有 " + lc.findRepeatNumber(nums));
    }

    public int findRepeatNumber(int[] nums) {

        int res = -1;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.add(num)) {
                res = num;
                break;
            }
        }

        return res;
    }
}
