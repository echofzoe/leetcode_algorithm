package algorithm.leetcode.双周赛.lc_2021_7_24_恒生;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 队列中可以看到的人数
 * <P>https://leetcode-cn.com/problems/number-of-visible-people-in-a-queue/</P>
 *
 * @author echofzoe
 * @since 2021.7.25
 */
public class Lc_5196_队列中可以看到的人数 {

    public static void main(String[] args) {
        Lc_5196_队列中可以看到的人数 lc = new Lc_5196_队列中可以看到的人数();

        int[] heights = {10, 6, 8, 5, 11, 9};
        
        System.out.println("有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。\n" +
                "一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。\n" +
                "请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。\n");
        System.out.println("输入：heights = " + Arrays.toString(heights));
        System.out.println("输出：" + Arrays.toString(lc.canSeePersonsCount(heights)));  // [3,1,2,1,1,0]
    }

    // 单调栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];

        // 单调栈 - 单调增
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            // 计算栈中有多少比当前人矮的
            while (!stack.isEmpty() && heights[i] > heights[stack.peekLast()]) {
                stack.pollFirst();
                res[i]++;
            }

            // 栈中有人比当前人高，故当前人能看到的人数到他为止了，即 +1
            res[i] += stack.isEmpty() ? 0 : 1;

            // 当前人入栈
            stack.offerLast(i);
        }

        return res;
    }

}
