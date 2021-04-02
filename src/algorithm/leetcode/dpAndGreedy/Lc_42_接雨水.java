package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Lc_42_接雨水 {

    // 接雨水
    // https://leetcode-cn.com/problems/trapping-rain-water/solution/

    public static void main(String[] args) {
        Lc_42_接雨水 lc = new Lc_42_接雨水();
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};
        int[] height3 = {4, 2, 0, 3, 2, 5, 1, 3, 2, 1, 2, 1};

        System.out.println("按数组" + Arrays.toString(height1) + "生成的池子能接" + lc.trapStack(height1) + "个单位的雨水");
        System.out.println("按数组" + Arrays.toString(height2) + "生成的池子能接" + lc.trapDoublePoint(height2) + "个单位的雨水");
        System.out.println("按数组" + Arrays.toString(height3) + "生成的池子能接" + lc.trapDP(height3) + "个单位的雨水");
    }

    // 暴力 - 时间复杂度 O(N^2) - 空间复杂度 O(1)
    public int trapBF(int[] height) {
        if (height == null || height.length == 0) return 0;

        int res = 0, n = height.length;

        for (int i = 1; i < n; i++) {
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < n; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }

        return res;
    }
    
    // 栈 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int trapStack(int[] height) {
        if (height == null || height.length == 0) return 0;

        int n = height.length;

        Deque<Integer> stack = new LinkedList<>();
        int res = 0, idx = 0;

        while (idx < n) {
            while (!stack.isEmpty() && height[idx] > height[stack.peekLast()]) {
                int low = stack.pollLast();  // idx 左边最矮的柱子

                if (stack.isEmpty()) continue;  // 如果栈内没有元素，说明当前位置左边没有比low高的柱子

                int top = stack.peekLast();  // idx 左边比其low高的柱子

                int w = idx - top + 1 - 2;  // 宽

                int h = Math.min(height[top], height[idx]) - height[low];

                res += w * h;
            }

            stack.offerLast(idx++);
        }

        return res;
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int trapDoublePoint(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int res = 0, maxLeft = 0, maxRight = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left++];
                } else {
                    res += maxLeft - height[left++];
                }
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right--];
                } else {
                    res += maxRight - height[right--];
                }
            }
        }

        return res;
    }
    
    // DP - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int trapDP(int[] height) {
        if (height == null || height.length == 0) return 0;

        int res = 0, n = height.length;
        int[] maxLeftDp = new int[n], maxRightDp = new int[n];
        // base case
        maxLeftDp[0] = height[0];
        maxRightDp[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            maxLeftDp[i] = Math.max(height[i], maxLeftDp[i - 1]);
            maxRightDp[n - 1 - i] = Math.max(height[n - 1 - i], maxRightDp[(n - 1 - i) + 1]);
        }

        for (int i = 0; i < n; i++) {
            int tmp = Math.min(maxLeftDp[i], maxRightDp[i]);
            if (tmp > height[i]) {
                res += tmp - height[i];
            }
        }

        return res;
    }

}
