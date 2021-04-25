package algorithm.leetcode.math.组合;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * <P>https://leetcode-cn.com/problems/combinations/</P>
 *
 * @author echofzoe
 * @since 2021.4.24
 */
public class Lc_77_组合 {

    public static void main(String[] args) {
        Lc_77_组合 lc = new Lc_77_组合();

        int n = 5, k = 3;

        System.out.println("1到" + n + "中所有可能的" + k + "个数的组合是" + lc.combine(n, k));
    }

    // dfs - 记 P = n个不同数中取k个数得组合次数，则 时间复杂度 O(P * k) - 空间复杂度 O(N)
    private List<List<Integer>> res;
    private List<Integer> tmp;
    public List<List<Integer>> combine(int n, int k) {
        this.res = new ArrayList<>();
        this.tmp = new ArrayList<>();

        dfs(n, k, 1);

        return res;
    }

    private void dfs(int n, int k, int i) {
        // 出口
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // 剪枝
        if (tmp.size() + (n - i + 1) < k) return;

        // 选当前数字
        tmp.add(i);
        dfs(n, k, i + 1);
        tmp.remove(tmp.size() - 1);

        // 不选当前数字
        dfs(n, k, i + 1);
    }

}
