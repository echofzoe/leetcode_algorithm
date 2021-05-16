package algorithm.leetcode.dfsAndBfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集
 * <P>https://leetcode-cn.com/problems/subsets/</P>
 *
 * @author echofzoe
 * @since 2021.5.16
 */
public class Lc_78_子集 {

    public static void main(String[] args) {
        Lc_78_子集 lc = new Lc_78_子集();

        int[] nums = {1, 2, 3};

        System.out.println("数组 " + Arrays.toString(nums) + " 中的元素互不相同，则该数组中所有可能的子集（不重复）有 " + lc.subsets1(nums));
    }

    // DFS 回溯 - 时间复杂度 O(N * 2^N) 一共2^N个状态，每种状态需要O(N)的时间来构造子集 - 空间复杂度 O(N) 为递归栈所需空间
    List<List<Integer>> res;
    List<Integer> cur;
    int n;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        cur = new ArrayList<>();
        n = nums.length;

        dfs(nums, 0);

        return res;
    }

    private void dfs(int[] nums, int idx) {
        if (idx == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        dfs(nums, idx + 1);
        cur.add(nums[idx]);
        dfs(nums, idx + 1);
        cur.remove(cur.size() - 1);
    }

    // 二进制表示法枚举所有子集 - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N)
    public List<List<Integer>> subsets1(int[] nums) {
        res = new ArrayList<>();
        cur = new ArrayList<>();
        n = nums.length;

        for (int mask = 0; mask < (1 << n); mask++) {
            cur.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    cur.add(nums[i]);
                }
            }
            res.add(new ArrayList<>(cur));
        }

        return res;
    }

}
