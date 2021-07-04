package algorithm.leetcode.bit.xor;

import java.util.Arrays;

/**
 * 错误的集合
 * <P>https://leetcode-cn.com/problems/set-mismatch/</P>
 *
 * @author echofzoe
 * @since 2021.7.4
 */
public class Lc_645_错误的集合 {

    public static void main(String[] args) {
        Lc_645_错误的集合 lc = new Lc_645_错误的集合();

        int[] nums = {1, 5, 3, 2, 2, 7, 6, 4, 8, 9};

        System.out.println("集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。\n" +
                "给定一个数组 nums 代表了集合 S 发生错误后的结果。\n" +
                "请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。\n");
        System.out.println("输入：" + Arrays.toString(nums));
        System.out.println("输出：" + Arrays.toString(lc.findErrorNums(nums)));  // {2, 10}
    }

    // 位运算 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;

        int xor = 0;
        for (int x : nums) xor ^= x;
        for (int i = 1; i <= n; i++) xor ^= i;

        int lowbit = xor & (-xor), a = 0, b = 0;

        for (int x : nums) {
            if ((lowbit & x) == 0) a ^= x;
            else b ^= x;
        }

        for (int i = 1; i <= n; i++) {
            if ((lowbit & i) == 0) a ^= i;
            else b ^= i;
        }

        for (int x : nums) {
            if (x == a)
                return new int[] {a, b};
        }

        return new int[] {b, a};
    }

    // 排序 + 模拟 - 时间复杂度 O(NlogN) 排序时间复杂度为O(NlogN)，遍历时间复杂度为O(N) - 空间复杂度 O(logN) 为排序所需额外空间
    public int[] findErrorNums1(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        int dup = 0, miss = 0;
        int pre = 0, cur = 0;

        for (int i = 0; i < n; i++) {
            cur = nums[i];
            if (cur == pre) dup = cur;
            else if (cur - pre > 1) miss = pre + 1;

            pre = cur;
        }

        if (nums[n - 1] != n) miss = n;

        return new int[] {dup, miss};
    }

}
