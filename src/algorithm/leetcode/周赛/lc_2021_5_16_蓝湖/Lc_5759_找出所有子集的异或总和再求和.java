package algorithm.leetcode.周赛.lc_2021_5_16_蓝湖;

import java.util.Arrays;

/**
 * 找出所有子集的异或总和再求和
 * <P>https://leetcode-cn.com/problems/sum-of-all-subset-xor-totals/</P>
 *
 * @author echofzoe
 * @since 2021.5.16
 */
public class Lc_5759_找出所有子集的异或总和再求和 {

    public static void main(String[] args) {
        Lc_5759_找出所有子集的异或总和再求和 lc = new Lc_5759_找出所有子集的异或总和再求和();

        int[] nums = {5, 1, 6};

        System.out.println("数组 " + Arrays.toString(nums) + " 的所有子集的异或总和再求和的结果是 " + lc.subsetXORSum(nums));  // 28
    }

    // DFS - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N) 为递归时栈空间的大小
    int n;
    int res;

    public int subsetXORSum(int[] nums) {
        n = nums.length;
        res = 0;

        dfs(nums, 0, 0);

        return res;
    }

    private void dfs(int[] nums, int idx, int xor) {
        if (idx == n) {
            res += xor;
            return;
        }

        dfs(nums, idx + 1, xor);  // 不选当前位置
        dfs(nums, idx + 1, xor ^ nums[idx]);  // 选取当前位置
    }

}
