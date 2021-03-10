package algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

public class Lc_224_基本计算器 {

    // 基本计算器
    // https://leetcode-cn.com/problems/basic-calculator/

    public static void main(String[] args) {
        Lc_224_基本计算器 lc = new Lc_224_基本计算器();
        String s = "(1+(4+5+2)-3)+(6+8)";

        System.out.println("字符串表达式\"" + s + "\"在经过计算器计算后得到的结果是" + lc.calculate(s));
    }

    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        int res = 0, curSign = 1;

        Deque<Integer> sign = new LinkedList<>();
        sign.offerLast(1);

        for (int i = 0; i < n; i++) {
            if (cs[i] == ' ') continue;

            switch (cs[i]) {
                case '+' -> curSign = sign.peekLast();
                case '-' -> curSign = -sign.peekLast();
                case '(' -> sign.offerLast(curSign);
                case ')' -> sign.pollLast();
                default -> {
                    int num = 0;
                    while (i < n && cs[i] >= '0' && cs[i] <= '9') {
                        num = num * 10 + (cs[i++] - 48);
                    }
                    i--;
                    res += curSign * num;
                }
            }
        }

        return res;
    }

}
