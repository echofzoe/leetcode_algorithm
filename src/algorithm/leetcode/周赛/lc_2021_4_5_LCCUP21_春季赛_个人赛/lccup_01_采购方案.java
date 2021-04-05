package algorithm.leetcode.周赛.lc_2021_4_5_LCCUP21_春季赛_个人赛;

import java.util.Arrays;

/**
 * 采购方案
 * <p>小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案</p>
 * <p>注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1</p>
 */
public class lccup_01_采购方案 {

    public static void main(String[] args) {
        lccup_01_采购方案 lc = new lccup_01_采购方案();

        int[] nums = {2, 2, 1, 9};
        int target = 10;
        
        System.out.println("预算" + target + "在报价单" + Arrays.toString(nums) + "中可以生成" + lc.purchasePlans(nums, target) + "种采购方案");  // 4
    }

    private static final int MOD = 1000000007;

    public int purchasePlans(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        int res = 0;

        for (int i = 0, j = n - 1; i < n - 1; i++) {
            while (i < j && nums[i] + nums[j] > target) j--;

            if (i == j) break;
            res = (res + j - i) % MOD;
        }

        return res % MOD;
    }

}
