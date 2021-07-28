package algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 字母大小写全排列
 * <P>https://leetcode-cn.com/problems/letter-case-permutation/</P>
 *
 * @author echofzoe
 * @since 2021.7.28
 */
public class Lc_784_字母大小写全排列 {

    public static void main(String[] args) {
        Lc_784_字母大小写全排列 lc = new Lc_784_字母大小写全排列();

        String S = "a1b2";
        
        System.out.println("给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。");
        System.out.println("输入：S = " + "\"" + S + "\"");
        System.out.println("输出：" + lc.letterCasePermutation(S));  // ["a1b2", "a1B2", "A1b2", "A1B2"]
    }

    // 回溯 - 时间复杂度 O(n * n!) - 空间复杂度 O(n)
    private List<String> res;
    private StringBuilder sb;
    private char[] cs;
    private int n;

    public List<String> letterCasePermutation(String s) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        cs = s.toCharArray();
        n = s.length();

        dfs(0);

        return res;
    }

    private void dfs(int idx) {
        // exit
        if (idx == n) {
            res.add(sb.toString());
            return;
        }

        // dfs
        char c = cs[idx];
        if (isNumber(c)) {
            sb.append(c);
            dfs(idx + 1);

            // backtrace
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(c);
            dfs(idx + 1);

            // backtrace
            sb.deleteCharAt(sb.length() - 1);

            sb.append(isCapital(c) ? (char) (c + 32) : (char) (c - 32));
            dfs(idx + 1);

            // backtrace
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isCapital(char c) {
        return c >= 'A' && c <= 'Z';
    }

}
