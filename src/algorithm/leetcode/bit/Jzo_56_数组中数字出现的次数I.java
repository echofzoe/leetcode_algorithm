package algorithm.leetcode.bit;

import java.util.Arrays;

public class Jzo_56_数组中数字出现的次数I {

    // 数组中数字出现的次数
    // https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/

    public static void main(String[] args) {
        Jzo_56_数组中数字出现的次数I lc = new Jzo_56_数组中数字出现的次数I();
        int[] nums = {1, 2, 5, 2};

        System.out.println("数组" + Arrays.toString(nums) + "中的两个只出现一次的数字是" + Arrays.toString(lc.singleNumbers(nums)));
    }

    // 分组异或 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }

        int rightMostBit = xor & (-xor);

        int x = 0;
        for (int i : nums) {
            if ((i & rightMostBit) == rightMostBit) x ^= i;
        }

        return new int[]{x, x ^ xor};
    }

}
