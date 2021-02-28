package algorithm.leetcode.周赛.lc_2021_2_28_蓝湖;

import java.util.Arrays;

public class Lc_5690_最接近目标价格的甜点成本 {

    // 最接近目标价格的甜点成本
    // https://leetcode-cn.com/problems/closest-dessert-cost/

    public static void main(String[] args) {
        Lc_5690_最接近目标价格的甜点成本 lc = new Lc_5690_最接近目标价格的甜点成本();
        int[] baseCosts = {1, 7}, toppingCosts = {3, 4};
        int target = 10;
        
        System.out.println("基料" + Arrays.toString(baseCosts) + "和辅料" + Arrays.toString(toppingCosts) + "按题意所能配出的成本最接近" + target + "的方案（若有多个，取成本较低的那个）的成本是" + lc.closestCostDfs(baseCosts,toppingCosts,target));
    }

    // dfs - 时间复杂度 O(N*M^3) N=baseCosts.length, M=toppingCosts.length - 空间复杂度 O(1)
    int res = Integer.MAX_VALUE;
    public int closestCostDfs(int[] baseCosts, int[] toppingCosts, int target) {
        int n = baseCosts.length, m = toppingCosts.length;

        for (int i = 0; i < n; i++) {
            int sum = baseCosts[i];
            if (sum == target) return target;

            if (Math.abs(baseCosts[i] - target) < Math.abs(res - target)) {
                res = baseCosts[i];
            }

            dfs(sum, toppingCosts, 0, target);

            if (res == target) return target;
        }

        return res;
    }

    private void dfs(int sum, int[] toppingCosts, int idx, int target) {
        if (Math.abs(sum - target) < Math.abs(res - target) || (Math.abs(sum - target) == Math.abs(res - target) && sum < res)) {
            res = sum;
        } else if (sum >= target) {
            return;
        }

        if (idx == toppingCosts.length) return;

        dfs(sum, toppingCosts, idx + 1, target);
        dfs(sum + toppingCosts[idx], toppingCosts, idx + 1, target);
        dfs(sum + toppingCosts[idx] * 2, toppingCosts, idx + 1, target);
    }

}
