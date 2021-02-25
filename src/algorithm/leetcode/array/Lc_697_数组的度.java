package algorithm.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_697_数组的度 {

    // 数组的度
    // https://leetcode-cn.com/problems/degree-of-an-array/

    public static void main(String[] args) {
        Lc_697_数组的度 lc = new Lc_697_数组的度();
        int[] nums = {1, 2, 2, 3, 1, 4, 2};

        System.out.println("与" + Arrays.toString(nums) + "拥有相同大小的度的最短连续子数组的长度是" + lc.findShortestSubArray(nums));
    }

    // 哈希 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;

        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;  // 频数（度）
                map.get(nums[i])[2] = i;  // 结束位置
            } else {
                map.put(nums[i], new int[] {1, i, i});
            }
        }

        int maxDegree = Integer.MIN_VALUE, res = 0;
        for (int[] arr : map.values()) {
            if (maxDegree < arr[0]) {
                maxDegree = arr[0];
                res = arr[2] - arr[1] + 1;
            } else if (maxDegree == arr[0]) {
                res = Math.min(res, arr[2] - arr[1] + 1);
            }
        }

        return res;
    }

}
