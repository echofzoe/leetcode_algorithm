package algorithm.leetcode.周赛.lc_2020_11_29_vivo;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_1673_找出最具竞争力的子序列 {

    // 找出最具竞争力的子序列
    // https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/

    public static void main(String[] args) {
        Lc_1673_找出最具竞争力的子序列 lc = new Lc_1673_找出最具竞争力的子序列();
        int[] input = {2, 4, 3, 3, 5, 4, 9, 6};
        int k = 4;

        System.out.println("整数数组" + Arrays.toString(input) + "中，长度为" + k + "的最具竞争力的子序列为" + Arrays.toString(lc.mostCompetitive(input, k)));
    }

    // 单调栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        if (k == n) return nums;

        int[] res = new int[k];
        Deque<Integer> stack = new LinkedList<>();
        int p = n - k;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && p > 0 && nums[i] < nums[stack.peekFirst()]) {
                stack.pollFirst();
                p--;
            }
            stack.offerFirst(i);
        }

        while (p > 0) {
            stack.pollFirst();
            p--;
        }

        while (!stack.isEmpty()) {
            res[--k] = nums[stack.pollFirst()];
        }

        return res;
    }

}
