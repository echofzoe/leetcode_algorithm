package algorithm.leetcode.bit.xor;

/**
 * 数组异或操作
 * <P>https://leetcode-cn.com/problems/xor-operation-in-an-array/</P>
 *
 * @author echofzoe
 * @since 2021.5.13
 */
public class Lc_1486_数组异或操作 {

    public static void main(String[] args) {
        Lc_1486_数组异或操作 lc = new Lc_1486_数组异或操作();

        int n = 4, start = 3;

        System.out.println("给你两个整数，n = " + n + " 和 start = " + start + "。\n" +
                "数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。\n" +
                "则 nums 中所有元素按位异或（XOR）后得到的结果是 " + lc.xorOperation(n, start));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int xorOperation(int n, int start) {
        int res = 0;

        for (int i = 0; i < n; i++) {
            res ^= start + 2 * i;
        }

        return res;
    }

}
