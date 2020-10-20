package algorithm.leetcode.string;

import java.util.Stack;

public class Lc_844_比较含退格的字符串 {

    // 比较含退格的字符串
    // https://leetcode-cn.com/problems/backspace-string-compare/

    public static void main(String[] args) {
        Lc_844_比较含退格的字符串 lc = new Lc_844_比较含退格的字符串();

        String S = "y#fo##f", T = "y#f#o##f";
        System.out.println("字符串 \"" + S + "\" 和 \"" + T + "\" 退格计算后" + (lc.backspaceCompare_Refactor(S, T) ? "相等" : "不相等"));
    }

    // 栈 - 时间复杂度 O(N + M) - 空间复杂度 O(N + M)
    public boolean backspaceCompare_Stack(String S, String T) {
        Stack<Character> s_stack = new Stack<>();
        Stack<Character> t_stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (s_stack.size() > 0) {
                    s_stack.pop();
                }
            } else {
                s_stack.push(S.charAt(i));
            }
        }

        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == '#') {
                if (t_stack.size() > 0) {
                    t_stack.pop();
                }
            } else {
                t_stack.push(T.charAt(i));
            }
        }

        if (s_stack.size() != t_stack.size()) return false;
        while (s_stack.size() > 0) {
            if (s_stack.pop() != t_stack.pop()) return false;
        }
        return true;
    }

    // 重构字符串 - 时间复杂度 O(N + M) - 空间复杂度 O(N + M)
    public boolean backspaceCompare_Refactor(String S, String T) {
        return refactor(S).equals(refactor(T));
    }

    private String refactor(String s) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
            } else {
                buf.append(s.charAt(i));
            }
        }

        return buf.toString();
    }
}
