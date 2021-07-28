package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全排列
 * <P>https://leetcode-cn.com/problems/permutations/</P>
 *
 * @author echofzoe
 * @since unknown
 * @updated 2021.7.28
 */
public class Lc_46_全排列 {

    public static void main(String[] args) {
        Lc_46_全排列 lc = new Lc_46_全排列();

        int[] nums = {1, 2, 3};

        System.out.println("给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.permute(nums));
    }

    private List<List<Integer>> res;
    private List<Integer> tmp;
    private int n;
    private int[] nums;
    private boolean[] vis;

    // 回溯（交换） - 时间复杂度 O(n * n!) - 空间复杂度 O(n)
    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        res = new ArrayList<>();
        this.nums = nums;

        dfs(0);

        return res;
    }

    private void dfs(int idx) {
        // exit
        if (idx == n) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        // dfs
        for (int i = idx; i < n; i++) {
            swap(idx, i);

            dfs(idx + 1);

            // backtrace
            swap(idx, i);
        }
    }

    private void swap(int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // 回溯（累加） - 时间复杂度 O(n * n!) - 空间复杂度 O(n)
    public List<List<Integer>> permute1(int[] nums) {
        n = nums.length;
        res = new ArrayList<>();
        tmp = new ArrayList<>();
        this.nums = nums;
        vis = new boolean[n];

        dfs1(0);

        return res;
    }

    private void dfs1(int idx) {
        // exit
        if (idx == n) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // dfs
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;

            vis[i] = true;
            tmp.add(nums[i]);
            dfs1(idx + 1);

            // backtrace
            tmp.remove(tmp.size() - 1);
            vis[i] = false;
        }
    }

}
