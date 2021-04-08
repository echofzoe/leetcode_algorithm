package algorithm.剑指offer;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Jz_38_字符串的排列 {

    public static void main(String[] args) {
        Jz_38_字符串的排列 jz = new Jz_38_字符串的排列();

        String s = "abc";

        System.out.println("字符串\"" + s + "\"中字符的所有排列为" + Arrays.toString(jz.permutation(s)));
    }

    // DFS - 时间复杂度 O(N! * N) - 空间复杂度 O(N^2)
    private List<String> res;
    private char[] cs;

    public String[] permutation(String s) {
        this.res = new LinkedList<>();
        this.cs = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(int idx) {
        if (idx == cs.length - 1) {
            res.add(String.valueOf(cs));
            return;
        }

        Set<Character> set = new HashSet<>();  // 在这里就进行剪枝，而不是把所有的结果都算出来后，再用Set进行去重
        for (int i = idx; i < cs.length; i++) {
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

}
