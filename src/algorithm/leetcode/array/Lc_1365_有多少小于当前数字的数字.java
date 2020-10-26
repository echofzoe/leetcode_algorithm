package algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

public class Lc_1365_有多少小于当前数字的数字 {

    // 有多少小于当前数字的数字
    // https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/

    public static void main(String[] args) {
        Lc_1365_有多少小于当前数字的数字 lc = new Lc_1365_有多少小于当前数字的数字();
        int[] input1 = {8, 1, 2, 2, 3};
        int[] input2 = {6, 5, 4, 8};

        System.out.println("数组" + Arrays.toString(input1) + "中对于每个元素,统计数组中比它小的所有数字的数目的结果集是 " + Arrays.toString(lc.smallerNumbersThanCurrent_CountSort(input1)));

        System.out.println("数组" + Arrays.toString(input2) + "中对于每个元素,统计数组中比它小的所有数字的数目的结果集是 " + Arrays.toString(lc.smallerNumbersThanCurrent_QuicklySort(input2)));
    }

    // 计数排序 - 时间复杂度 O(N+K) K为值域大小 - 空间复杂度 O(K) 需额外开辟一个值域大小的数组
    public int[] smallerNumbersThanCurrent_CountSort(int[] nums) {
        int[] cnt = new int[101];    // nums[i] 的值域为 [0,100]

        // 统计数组中每个数出现的频次
        for (int num : nums) {
            cnt[num]++;
        }

        // 统计 <=i 的数在数组中出现的频次
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }

        // 统计结果
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];    // 小于,而非小于等于
        }

        return res;
    }

    // 快速排序 - 时间复杂度 O(N+K) K为值域大小 - 空间复杂度 O(K) 需额外开辟一个值域大小的数组
    public int[] smallerNumbersThanCurrent_QuicklySort(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }

        Arrays.sort(data, (o1, o2) -> o1[0] - o2[0]);

        int[] res = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1 || data[i][0] != data[i - 1][0]) {
                prev = i;
            }
            res[data[i][1]] = prev;
        }

        return res;
    }
}
