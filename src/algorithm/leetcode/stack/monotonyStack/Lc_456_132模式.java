package algorithm.leetcode.stack.monotonyStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_456_132模式 {

    // 132模式
    // https://leetcode-cn.com/problems/132-pattern/

    public static void main(String[] args) {
        Lc_456_132模式 lc = new Lc_456_132模式();
        int[] nums = {3, 5, 0, 3, 4};

        System.out.println("序列" + Arrays.toString(nums) + "中" + (lc.find132pattern(nums) ? "存在" : "不存在") + "132模式的子序列");
    }

    // 单调栈，枚举3（即枚举j）- 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // 提前准备好每个位置左边的最小值
        int[] leftMin = new int[n];
        Arrays.fill(leftMin, Integer.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
        }

        // 枚举3
        Deque<Integer> monoStk = new LinkedList<>();
        for (int j = n - 1; j > 0; j--) {
            int k = Integer.MAX_VALUE;

            while (!monoStk.isEmpty() && nums[j] > monoStk.peekLast()) {
                // 3进栈，2出栈
                k = monoStk.pollLast();
            }

            // 32有了，选择3左边最小的1，判断132是否成立
            if (leftMin[j] < k) {
                return true;
            }

            monoStk.offerLast(nums[j]);
        }

        return false;
    }

    // 暴力 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public boolean find132patternBF(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // 枚举3，也就是j，找其左边最小元素，与其右边小于它的最大元素
        for (int j = 1; j < n - 1; j++) {
            int i = j - 1, k = j + 1;
            int minI = Integer.MAX_VALUE, maxK = Integer.MIN_VALUE;

            while (i >= 0) {
                minI = Math.min(minI, nums[i]);
                if (i == 0) break;
                i--;
            }

            while (k <= n - 1) {
                if (nums[k] < nums[j]) {
                    maxK = Math.max(maxK, nums[k]);
                }
                if (k == n - 1) break;
                k++;
            }

            if (minI < maxK && maxK < nums[j]) {
                return true;
            }
        }

        return false;
    }

}
