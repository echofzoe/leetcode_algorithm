package algorithm.leetcode.stack.calculate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Lc_772_III_基本计算器 {

    // 基本计算器 III
    // https://leetcode-cn.com/problems/basic-calculator-iii/

    public static void main(String[] args) {
        Lc_772_III_基本计算器 lc = new Lc_772_III_基本计算器();
        String s1 = "(2+6*3+5-(3*14/7+2)*5)+3"; // -12
        String s2 = "2*(5+5*2)/3+(6/2+8)"; // 21

        System.out.println("实现一个基本的计算器来计算简单的表达式字符串。");
        System.out.println("表达式字符串只包含非负整数，算符 +、-、*、/，左括号 ( 和右括号 )。整数除法需要向下截断。");
        System.out.println("你可以假定给定的表达式总是有效的。所有的中间结果的范围为 [-2^31, 2^31 - 1]。");
        System.out.println("- 使用这个基本计算器计算" + s1 + "的结果是" + lc.calculate(s1));
        System.out.println("- 使用这个基本计算器计算" + s2 + "的结果是" + lc.calculate(s2));
    }

    // 双栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    private Map<Character, Integer> priority = new HashMap<>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
    }};

    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> opes = new ArrayDeque<>();

        // 预处理字符串
        nums.offerLast(0); // 避免第一个数字是负数
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "(0-"); // 避免左括号内第一个数字是负数
        s = s.replaceAll("\\(\\+", "(0+"); // 避免左括号内第一个正数前有加号

        char[] cs = s.toCharArray();
        int n = cs.length;

        for (int i = 0; i < n; i++) {
            char c = cs[i];

            switch (c) {
                case '(' -> opes.offerLast(c);
                case ')' -> {
                    while (!opes.isEmpty()) {
                        if (opes.peekLast() != '(') {
                            calc(nums, opes);
                        } else {
                            opes.pollLast();
                            break;
                        }
                    }
                }
                default -> {
                    if (Character.isDigit(c)) {
                        int a = 0, idx = i;
                        while (idx < n && Character.isDigit(cs[idx])) {
                            a = a * 10 + (cs[idx++] - '0');
                        }

                        i = idx - 1;
                        nums.offerLast(a);
                    } else {
                        while (!opes.isEmpty() && opes.peekLast() != '(') {
                            char prev = opes.peekLast();
                            if (priority.get(prev) >= priority.get(c)) {
                                calc(nums, opes);
                            } else {
                                break;
                            }
                        }

                        opes.offerLast(c);
                    }
                }
            }
        }

        while (!opes.isEmpty()) {
            calc(nums, opes);
        }

        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> opes) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (opes.isEmpty()) return;

        Integer b = nums.pollLast(), a = nums.pollLast();
        char c = opes.pollLast();

        int num = 0;
        switch (c) {
            case '+' -> num = a + b;
            case '-' -> num = a - b;
            case '*' -> num = a * b;
            default -> num = a / b;
        }

        nums.offerLast(num);
    }

}
