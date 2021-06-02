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
 * @updated 2021.6.2
 */
public class Lc_46_全排列 {

    public static void main(String[] args) {
        Lc_46_全排列 lc = new Lc_46_全排列();

        int[] input = {1, 2, 3};

        System.out.println("没有重复数字的序列" + Arrays.toString(input) + "的所有可能的全排列有" + Arrays.deepToString(lc.permute(input).toArray()));
    }

    // 回溯 - 时间复杂度 O() - 空间复杂度 O()
    private List<List<Integer>> res;
    private List<Integer> tmp;
    private int n;

    public List<List<Integer>> permute(int[] nums) {
        this.res = new ArrayList<>();
        this.tmp = new ArrayList<>();
        this.n = nums.length;

        tmp = Arrays.stream(nums).boxed().collect(Collectors.toList());

        dfs(tmp, 0);

        return res;
    }

    private void dfs(List<Integer> tmp, int idx) {
        if (idx == n) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = idx; i < n; i++) {
            swap(tmp, idx, i);
            dfs(tmp, idx + 1);
            // backtrace
            swap(tmp, idx, i);
        }
    }

    private void swap(List<Integer> tmp, int i, int j) {
        int t = tmp.get(i);
        tmp.set(i, tmp.get(j));
        tmp.set(j, t);
    }

}
