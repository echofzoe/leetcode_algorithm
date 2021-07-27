package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.*;

/**
 * 得到子序列的最少操作次数
 * <P>https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.7.27
 */
public class Lc_1713_得到子序列的最少操作次数 {

    public static void main(String[] args) {
        Lc_1713_得到子序列的最少操作次数 lc = new Lc_1713_得到子序列的最少操作次数();

        int[] target = {5, 1, 3}, arr = {9, 4, 2, 3, 4};
        
        System.out.println("给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。\n" +
                "每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。\n" +
                "请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。\n" +
                "一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。\n");
        System.out.println("输入：target = " + Arrays.toString(target) + ", arr = " + Arrays.toString(arr));
        System.out.println("输出：" + lc.minOperations(target, arr));  // 2
    }

    // 贪心 + 二分 - 时间复杂度 O(N + MlogM) - 空间复杂度 O(N + M) N为target的长度，M为arr的长度
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;

        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m.put(target[i], i);
        }

        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            if (m.containsKey(x)) {
                list.add(m.get(x));
            }
        }
        arr = list.stream().mapToInt(Integer::valueOf).toArray();

        return n - lengthOfLIS(arr);
    }

    private int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int[] aux = new int[n];
        // base case
        aux[0] = nums[0];

        int end = 0;

        for (int i = 1; i < n; i++) {
            if (aux[end] < nums[i]) {
                aux[++end] = nums[i];
            } else {
                int l = 0, m, r = end;

                while (l < r) {
                    m = l + (r - l) / 2;

                    if (aux[m] < nums[i]) l = m + 1;
                    else r = m;
                }

                aux[l] = nums[i];
            }
        }

        return ++end;
    }
    
}
