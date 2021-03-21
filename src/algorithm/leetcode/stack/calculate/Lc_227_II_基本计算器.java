package algorithm.leetcode.stack.calculate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Lc_227_II_基本计算器 {

    // 基本计算器 II
    // https://leetcode-cn.com/problems/basic-calculator-ii/

    public static void main(String[] args) {
        Lc_227_II_基本计算器 lc = new Lc_227_II_基本计算器();
        String s = "0-2147483647";

        System.out.println("字符串表达式\"" + s + "\"在经过计算器计算后得到的结果是" + lc.calculate1(s));
    }

    // 数字栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int calculate(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        Deque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int nums = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] >= '0' && cs[i] <= '9') {
                nums = nums * 10 + (cs[i] - 48);
            } else if (cs[i] != ' ') {
                process(stack, preSign, nums);

                preSign = cs[i];
                nums = 0;
            }
        }

        if (nums != 0) {
            process(stack, preSign, nums);
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollLast();
        }

        return res;
    }

    private void process(Deque<Integer> stack, char preSign, int nums) {
        switch (preSign) {
            case '+' -> stack.offerLast(nums);
            case '-' -> stack.offerLast(-nums);
            case '*' -> stack.offerLast(stack.pollLast() * nums);
            default -> stack.offerLast(stack.pollLast() / nums);
        }
    }


    // 双栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    private Map<Character, Integer> priority = new HashMap<>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
    }};

    public int calculate1(String s) {
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

        while (!opes.isEmpty()) {
            calc(nums, opes);
        }

        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> opes) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (opes.isEmpty()) return;

        int b = nums.pollLast(), a = nums.pollLast();
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
