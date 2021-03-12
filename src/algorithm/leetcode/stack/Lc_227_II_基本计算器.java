package algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Lc_227_II_基本计算器 {

    // 基本计算器 II
    // https://leetcode-cn.com/problems/basic-calculator-ii/

    public static void main(String[] args) {
        Lc_227_II_基本计算器 lc = new Lc_227_II_基本计算器();
        String s = "0-2147483647";

        System.out.println("字符串表达式\"" + s + "\"在经过计算器计算后得到的结果是" + lc.calculate(s));
    }

    // 数字栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] >= '0' && cs[i] <= '9') {
                num = num * 10 + (cs[i] - 48);
            } else if (cs[i] != ' ') {
                process(stack, preSign, num);

                preSign = cs[i];
                num = 0;
            }
        }

        if (num != 0) {
            process(stack, preSign, num);
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollLast();
        }

        return res;
    }

    private void process(Deque<Integer> stack, char preSign, int num) {
        switch (preSign) {
            case '+' -> stack.offerLast(num);
            case '-' -> stack.offerLast(-num);
            case '*' -> stack.offerLast(stack.pollLast() * num);
            default -> stack.offerLast(stack.pollLast() / num);
        }
    }

}
