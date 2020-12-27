package algorithm.leetcode.stack.monotonyStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Lc_84_柱状图中最大的矩形 {

    // 柱状图中最大的矩形
    // https://leetcode-cn.com/problems/largest-rectangle-in-histogram/

    public static void main(String[] args) {
        Lc_84_柱状图中最大的矩形 lc = new Lc_84_柱状图中最大的矩形();
        int[] heights = {2, 1, 5, 6, 2, 3};
    
        System.out.println("柱状图" + Arrays.toString(heights) + "中，能勾勒出的矩形的最大面积是" + lc.largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peekFirst()] >= heights[i]) stack.pollFirst();

            left[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.offerFirst(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();

            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        
        return res;
    }

}
