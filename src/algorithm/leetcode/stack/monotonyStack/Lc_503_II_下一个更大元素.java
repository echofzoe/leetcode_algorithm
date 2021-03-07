package algorithm.leetcode.stack.monotonyStack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_503_II_下一个更大元素 {

    // 下一个更大元素 II
    // https://leetcode-cn.com/problems/next-greater-element-ii/

    public static void main(String[] args) {
        Lc_503_II_下一个更大元素 lc = new Lc_503_II_下一个更大元素();
        int[] nums = {1,2,1};
        
        System.out.println("循环数组" + Arrays.toString(nums) + "每个元素的下一个更大元素（不存在则输出-1）组成的数组是" + Arrays.toString(lc.nextGreaterElements(nums)));
    }

    // 单调栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        Arrays.fill(res, -1);

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peekLast()]) {
                res[stack.pollLast()] = nums[i % n];
            }

            stack.offerLast(i % n);
        }

        return res;
    }

}
