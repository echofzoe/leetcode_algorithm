package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 II
 * <P>https://leetcode-cn.com/problems/permutations-ii/</P>
 *
 * @author echofzoe
 * @since 2021.7.28
 */
public class Lc_47_全排列_II {

    public static void main(String[] args) {
        Lc_47_全排列_II lc = new Lc_47_全排列_II();

        int[] nums = {1, 1, 2};
        
        System.out.println("给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.permuteUnique(nums));
    }

    // 回溯 - 时间复杂度 O(n * n!) - 空间复杂度 O(n)
    private List<List<Integer>> res;
    private List<Integer> tmp;
    private int n;
    private int[] nums;
    private boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        tmp = new ArrayList<>();
        this.n = nums.length;
        this.nums = nums;
        vis = new boolean[n];

        Arrays.sort(nums);

        dfs(0);

        return res;
    }

    private void dfs(int idx) {
        // exit
        if (idx == n) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // dfs
        for (int i = 0; i < n; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) continue;

            vis[i] = true;
            tmp.add(nums[i]);

            dfs(idx + 1);

            // backtrace
            vis[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

}
