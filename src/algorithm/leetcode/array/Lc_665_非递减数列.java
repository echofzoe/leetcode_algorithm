package algorithm.leetcode.array;

import java.util.Arrays;

public class Lc_665_非递减数列 {

    // 非递减数列
    // https://leetcode-cn.com/problems/non-decreasing-array/

    public static void main(String[] args) {
        Lc_665_非递减数列 lc = new Lc_665_非递减数列();
        int[] nums = {3,4,2,3};
        
        System.out.println(Arrays.toString(nums) + (lc.checkPossibility(nums) ? "能" : "不能") + "在只改变一个元素的情况下将其变为非递减数列");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;

        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            int a = nums[i], b = nums[i + 1];

            if (a > b) {
                if (++cnt > 1) return false;

                if (i > 0 && b < nums[i - 1]) {
                    nums[i + 1] = a;
                }
            }
        }

        return true;
    }

}
