package algorithm.leetcode.dfsAndBfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 * <P>https://leetcode-cn.com/problems/subsets-ii/</P>
 *
 * @author echofzoe
 * @since 2021.5.16
 */
public class Lc_90_子集_II {

    public static void main(String[] args) {
        Lc_90_子集_II lc = new Lc_90_子集_II();

        int[] nums = {1, 2, 2};

        System.out.println("数组 " + Arrays.toString(nums) + " 中可能含有相同元素，则该数组中所有可能的子集（不重复）有 " + lc.subsetsWithDup1(nums));
    }

    // DFS 回溯 - 时间复杂度 O(N * 2^N) 一共2^N个状态，每种状态需要O(N)的时间来构造子集 - 空间复杂度 O(N) 为递归栈所需空间
    List<List<Integer>> res;
    List<Integer> cur;
    int n;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        cur = new ArrayList<>();
        n = nums.length;

        Arrays.sort(nums);  // 先排序，具体原因见dfs方法中的剪枝操作
        dfs(nums, 0, false);

        return res;
    }

    private void dfs(int[] nums, int idx, boolean choosePre) {
        if (idx == n) {
            res.add(new ArrayList<>(cur));
            return;
        }

        dfs(nums, idx + 1, false);
        // 若没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
        if (!choosePre && idx > 0 && nums[idx - 1] == nums[idx]) return;

        cur.add(nums[idx]);
        dfs(nums, idx + 1, true);
        cur.remove(cur.size() - 1);
    }

    // 二进制表示法枚举所有子集 - 时间复杂度 O(N * 2^N) - 空间复杂度 O(N)
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        res = new ArrayList<>();
        cur = new ArrayList<>();
        n = nums.length;

        Arrays.sort(nums);
        for (int mask = 0; mask < (1 << n); mask++) {
            cur.clear();
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    // 若没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i - 1] == nums[i]) {
                        valid = false;
                        break;
                    }

                    cur.add(nums[i]);
                }
            }

            if (valid) res.add(new ArrayList<>(cur));
        }

        return res;
    }

}
