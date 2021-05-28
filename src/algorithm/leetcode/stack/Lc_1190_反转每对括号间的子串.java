package algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 反转每对括号间的子串
 * <P>https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/</P>
 *
 * @author echofzoe
 * @since 2021.5.27
 */
public class Lc_1190_反转每对括号间的子串 {

    public static void main(String[] args) {
        Lc_1190_反转每对括号间的子串 lc = new Lc_1190_反转每对括号间的子串();

        String s = "(ed(et(oc))el)";

        System.out.println("给出一个字符串 s（仅含有小写英文字母和括号）。\n" +
                "请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。\n" +
                "注意，您的结果中 不应 包含任何括号。");
        System.out.println("输入：s = \"" + s + "\"");
        System.out.println("输出：" + lc.reverseParentheses(s));
    }

    // 栈 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    public String reverseParentheses(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        Deque<String> dq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = cs[i];

            if (c == '(') {
                dq.offerLast(sb.toString());
                sb.setLength(0);
            } else if (c == ')') {
                sb.reverse();
                sb.insert(0, dq.pollLast());
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
