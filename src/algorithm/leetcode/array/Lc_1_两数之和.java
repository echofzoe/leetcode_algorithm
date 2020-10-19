package algorithm.leetcode.array;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_1_两数之和 {

    // 两数之和
    // https://leetcode-cn.com/problems/two-sum/

    public static void main(String[] args) {
        Lc_1_两数之和 lc = new Lc_1_两数之和();
        int[] input = new int[]{3, 2, 4};
        int target = 6;

        System.out.println("数组 " + Arrays.toString(input) + " 中两数之和等于 " + target + " 的两数下标是 " + Arrays.toString(lc.twoSum_HashTable(input, target)));
    }

    // 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] twoSum_HashTable(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[] {hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int[] twoSum_DoublePoint(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums.length < 2) return new int[0];    // 特判

        int[] res = new int[2];    // 结果集

        // 双指针一趟遍历有序数组得到结果集的数值
        int[] foo = new int[nums.length];
        System.arraycopy(nums, 0, foo, 0, nums.length);
        Arrays.sort(foo);
        int i = 0, j = foo.length - 1, tmp1 = 0, tmp2 = 0;
        while (i < j) {
            int sum = foo[i] + foo[j];

            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                tmp1 = foo[i];
                tmp2 = foo[j];
                break;
            }
        }

        // 一趟遍历原生数组得到结果集数值的索引
        boolean flag = true;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == tmp1 && flag) {
                res[0] = k;
                flag = false;
            }
            if (nums[k] == tmp2) {
                res[1] = k;
            }
        }

        return res;
    }
}
