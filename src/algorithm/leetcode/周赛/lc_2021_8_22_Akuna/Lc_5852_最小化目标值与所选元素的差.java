package algorithm.leetcode.周赛.lc_2021_8_22_Akuna;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 最小化目标值与所选元素的差
 * <P>https://leetcode-cn.com/problems/minimize-the-difference-between-target-and-chosen-elements/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_5852_最小化目标值与所选元素的差 {

    public static void main(String[] args) {
        Lc_5852_最小化目标值与所选元素的差 lc = new Lc_5852_最小化目标值与所选元素的差();

        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int target = 13;

        System.out.println("给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。\n" +
                "从矩阵的 每一行 中选择一个整数，你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。\n" +
                "返回 最小的绝对差 。\n" +
                "a 和 b 两数字的 绝对差 是 a - b 的绝对值。\n");
        System.out.println("输入：mat = " + Arrays.deepToString(mat) + ", target = " + target);
        System.out.println("输出：" + lc.minimizeTheDifference(mat, target));  // 0
    }

    // dfs - 时间复杂度 O(70 * 70) - 空间复杂度 O(70 * 4900)
    private int res, tar, m, n;
    private int[][] mat;
    private boolean[][] vis;

    public int minimizeTheDifference(int[][] mat, int target) {
        res = Integer.MAX_VALUE;
        tar = target;
        m = mat.length;
        n = mat[0].length;
        this.mat = mat;
        vis = new boolean[70][4900];

        for (int i = 0; i < m; i++) Arrays.sort(mat[i]);

        dfs(0, 0);

        return res;
    }

    private void dfs(int r, int sum) {
        // exit
        if (r == m) {
            res = Math.min(res, Math.abs(tar - sum));
            return;
        }

        // pruning
        if (sum - tar > res || vis[r][sum]) return;

        // memorize
        vis[r][sum] = true;

        // dfs
        for (int i = 0; i < n; i++) {
            dfs(r + 1, sum + mat[r][i]);
        }
    }

}
