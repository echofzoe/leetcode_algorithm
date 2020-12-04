package algorithm.leetcode.dpAndGreedy;

import java.util.*;

public class Lc_659_分割数组为连续子序列 {

    // 分割数组为连续子序列
    // https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/

    public static void main(String[] args) {
        Lc_659_分割数组为连续子序列 lc = new Lc_659_分割数组为连续子序列();
        int[] input1 = {1, 2, 3, 3, 4, 4, 5, 5};
        int[] input2 = {1, 2, 3, 4, 4, 5};

        System.out.println("按升序排序的整数数组" + Arrays.toString(input1) + (lc.isPossibleGreedy(input1) ? "能" : "不能") + "分割成一个或多个子序列");
        System.out.println("按升序排序的整数数组" + Arrays.toString(input2) + (lc.isPossibleGreedy(input2) ? "能" : "不能") + "分割成一个或多个子序列");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public boolean isPossibleGreedy(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> countMap = new HashMap<>();  // 统计数字出现频次
        Map<Integer, Integer> endMap = new HashMap<>();  // 统计以当前数字为结尾的子序列个数

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);  // 当前数字的出现频次
            if (count > 0) {
                int prevEndCount = endMap.getOrDefault(num - 1, 0);  // 以当前数字的前一个数字为结尾的子序列个数
                if (prevEndCount > 0) {
                    countMap.put(num, count - 1);
                    endMap.put(num - 1, prevEndCount - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
