package algorithm.剑指offer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * @author echofzoe
 * @since 2021.4.8
 */
public class Jzo_31_栈的压入与弹出序列 {

    public static void main(String[] args) {
        Jzo_31_栈的压入与弹出序列 jz = new Jzo_31_栈的压入与弹出序列();

        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 3, 5, 1, 2};

        System.out.println("序列" + Arrays.toString(popped) + (jz.validateStackSequences(pushed, popped) ? "是" : "不是") + "按顺序" + Arrays.toString(pushed) + "压栈的栈的弹出序列");
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        int n = pushed.length;

        Deque<Integer> stack = new LinkedList<>();

        int i = 0, j = 0;
        while (i < n) {
            stack.offerLast(pushed[i]);

            while (!stack.isEmpty() && stack.peekLast() == popped[j]) {
                stack.pollLast();
                j++;
            }

            i++;
        }

        return stack.isEmpty();
    }

}
