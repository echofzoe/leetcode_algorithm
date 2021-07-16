package algorithm.剑指offer.backtrace;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Jzo_38_字符串的排列 {

    public static void main(String[] args) {
        Jzo_38_字符串的排列 jz = new Jzo_38_字符串的排列();

        String s = "abc";

        System.out.println("字符串\"" + s + "\"中字符的所有排列为" + Arrays.toString(jz.permutation(s)));
    }

    private int n;
    private List<String> res;
    private char[] cs;
    private StringBuilder sb;
    private boolean[] vis;

    // 回溯（交换） - 时间复杂度 O(N! * N) - 空间复杂度 O(N^2) 为递归过程中辅助Set累计存储的最大数量
    public String[] permutation(String s) {
        n = s.length();
        res = new LinkedList<>();
        cs = s.toCharArray();

        dfs(0);

        return res.toArray(new String[0]);
    }

    private void dfs(int idx) {
        if (idx == n - 1) {
            res.add(String.valueOf(cs));
            return;
        }

        Set<Character> set = new HashSet<>();  // 在这里就进行剪枝，而不是把所有的结果都算出来后，再用Set进行去重
        for (int i = idx; i < n; i++) {
            if (set.contains(cs[i])) continue;  // 重复，剪枝
            set.add(cs[i]);

            swap(idx, i);  // 将 cs[i] 固定在第 idx 位
            dfs(idx + 1);  // 开始固定第 idx + 1 位字符
            swap(idx, i);  // 回溯
        }
    }

    private void swap(int a, int b) {
        char tmp = cs[a];
        cs[a] = cs[b];
        cs[b] = tmp;
    }

    // 回溯（累加） - 时间复杂度 O(N! * N) - 空间复杂度 O(N)
    public String[] permutation1(String s) {
        n = s.length();
        res = new LinkedList<>();
        cs = s.toCharArray();
        sb = new StringBuilder();
        vis = new boolean[n];

        Arrays.sort(cs);

        dfs1(0);

        return res.toArray(new String[0]);
    }

    private void dfs1(int idx) {
        // exit
        if (idx == n) {
            res.add(sb.toString());
            return;
        }

        // dfs
        for (int i = 0; i < n; i++) {
            if (vis[i] || (i > 0 && !vis[i - 1] && cs[i] == cs[i - 1])) continue;

            vis[i] = true;
            sb.append(cs[i]);
            dfs1(idx + 1);

            // backtrace
            sb.deleteCharAt(sb.length() - 1);
            vis[i] = false;
        }
    }

}
