package algorithm.leetcode.周赛.lc_2021_2_7;

import java.util.Arrays;

public class Lc_5672_检查数组是否经排序和轮转得到 {

    // 检查数组是否经排序和轮转得到
    // https://leetcode-cn.com/problems/check-if-array-is-sorted-and-rotated/

    public static void main(String[] args) {
        Lc_5672_检查数组是否经排序和轮转得到 lc = new Lc_5672_检查数组是否经排序和轮转得到();
        int[] nums = {2, 1, 3, 4};

        System.out.println("数组" + Arrays.toString(nums) + (lc.check(nums) ? "能" : "不能") + "由源数组经轮转得到");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public boolean check(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                for (int j = i + 1; j < i + n; j++) {
                    int idx = j % n, pre = (j - 1) % n;
                    if (nums[idx] < nums[pre]) return false;
                }

                return true;
            }
        }

        return true;
    }

}
