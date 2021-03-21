package algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Lc_150_逆波兰表达式求值 {

    // 逆波兰表达式求值
    // https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/

    public static void main(String[] args) {
        Lc_150_逆波兰表达式求值 lc = new Lc_150_逆波兰表达式求值();
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        String[] tokens1 = {"4","3","-"};

        System.out.println("用逆波兰表达法计算序列" + Arrays.toString(tokens) + "得到的结果是" + lc.evalRPN(tokens));
        System.out.println("用逆波兰表达法计算序列" + Arrays.toString(tokens1) + "得到的结果是" + lc.evalRPN(tokens1));
    }

    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String c : tokens) {
            switch(c) {
                case "+" -> stack.offerLast(stack.pollLast() + stack.pollLast());
                case "-" -> stack.offerLast(-(stack.pollLast() - stack.pollLast()));
                case "*" -> stack.offerLast(stack.pollLast() * stack.pollLast());
                case "/" -> {
                    int rightOpe = stack.pollLast();
                    int leftOpe = stack.pollLast();
                    stack.offerLast(leftOpe / rightOpe);
                }
                default -> stack.offerLast(Integer.parseInt(c));
            }
        }

        return stack.pollLast();
    }

}
