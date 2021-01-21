package algorithm.leetcode.bit;

import java.util.Arrays;

public class Lc_645_错误的集合 {

    // 错误的集合
    // https://leetcode-cn.com/problems/set-mismatch/

    // PS: -x = ~x + 1 = ~(x - 1)

    public static void main(String[] args) {
        Lc_645_错误的集合 lc = new Lc_645_错误的集合();
//        int[] nums = {1, 2, 2, 4};
        int[] nums = {1, 2, 4, 4, 5, 6};

        System.out.println(Arrays.toString(nums) + "中重复出现的整数和丢失的整数分别是" + Arrays.toString(lc.findErrorNums3(nums)));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] findErrorNums1(int[] nums) {
        int n = nums.length;
        int dup = -1, mis = -1;

        for (int i : nums) {
            int cur = Math.abs(i);
            if (nums[cur - 1] > 0) {
                nums[cur - 1] *= -1;
            } else {
                dup = -nums[cur - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                mis = i + 1;
            }
        }

        return new int[]{dup, mis};
    }

    // 排序 - 时间复杂度 O(NlogN) - 空间复杂度 O(logN)
    public int[] findErrorNums2(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, mis = -1, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                dup = nums[i];
            } else if (nums[i] > nums[i - 1] + 1) {
                mis = nums[i - 1] + 1;
            }
        }

        return new int[] {dup, nums[n - 1] == n ? mis : n};
    }

    // 异或 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] findErrorNums3(int[] nums) {
        int xor = 0, n = nums.length;

        for (int i = 1; i <= n; i++) {
            xor ^= nums[i - 1];
            xor ^= i;
        }

        int rightMostBit = xor & (-xor);
        // -xor = ~(xor - 1) = ~xor + 1

        int a = 0, b = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & rightMostBit) == 0) a ^= i;
            else b ^= i;

            if ((nums[i - 1] & rightMostBit) == 0) a ^= nums[i - 1];
            else b ^= nums[i - 1];
        }

        for (int i : nums) {
            if (i == a) {
                return new int[] {a, b};
            }
        }

        return new int[] {b, a};
    }
}
