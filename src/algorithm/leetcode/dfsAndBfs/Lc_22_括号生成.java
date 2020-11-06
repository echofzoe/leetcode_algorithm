package algorithm.leetcode.dfsAndBfs;

import java.util.LinkedList;
import java.util.List;

public class Lc_22_括号生成 {

    // 括号生成
    // https://leetcode-cn.com/problems/generate-parentheses/

    public static void main(String[] args) {
        Lc_22_括号生成 lc = new Lc_22_括号生成();
        int n = 3;

        System.out.println(n + "对括号能够生成的所有可能并有效的括号组合是" + lc.generateParenthesis(n).toString());
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        int left = 0, right = 0;
        dfs(n, res, "", 0, 0);
        return res;
    }

    private void dfs(int n, List<String> res, String path, int left, int right) {
        if (right > left || left > n) return;
        if (left == right && left == n) {
            res.add(path);
            return;
        }
        dfs(n, res, path + '(', left + 1, right);
        dfs(n, res, path + ')', left, right + 1);
    }

}
