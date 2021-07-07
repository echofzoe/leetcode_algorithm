package algorithm.leetcode.doublePoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <P>https://leetcode-cn.com/problems/two-sum/</P>
 *
 * @author echofzoe
 * @updated 2021.7.7
 * @since unknown
 */
public class Lc_1_两数之和 {

    public static void main(String[] args) {
        Lc_1_两数之和 lc = new Lc_1_两数之和();

        int[] nums = new int[]{7, 2, 11, 15};
        int target = 9;

        System.out.println("给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "你可以按任意顺序返回答案。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("输出：" + Arrays.toString(lc.twoSum1(nums, target)));  // [0,1]
    }

    // 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] twoSum(int[] nums, int target) {
        int n;
        if (nums == null || (n = nums.length) < 2) return new int[0];

        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            if (m.containsKey(target - cur)) {
                return new int[]{m.get(target - cur), i};
            }

            m.put(cur, i);
        }

        return new int[0];
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] twoSum1(int[] nums, int target) {
        int n;
        if (nums == null || (n = nums.length) < 2) return new int[0];

        int[] foo = new int[n];
        System.arraycopy(nums, 0, foo, 0, n);
        Arrays.sort(foo);

        // 双指针一趟遍历有序数组得到结果集的数值
        int i = 0, j = n - 1, a = -1, b = -1;
        while (i < j) {
            int sum = foo[i] + foo[j];

            if (sum < target) i++;
            else if (sum > target) j--;
            else {
                a = foo[i];
                b = foo[j];
                break;
            }
        }

        if (a == -1) return new int[0];
        int[] res = new int[2];

        // 一趟遍历原生数组得到结果集数值的索引
        boolean flag = true;
        for (int k = 0; k < n; k++) {
            int cur = nums[k];
            if (a == cur && flag) {
                res[0] = k;
                flag = false;
            }

            if (b == cur) {
                res[1] = k;
            }
        }

        return res;
    }
}
