package algorithm.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 存在重复元素 III
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 *
 * @author echofzoe
 * @since 2021.4.17
 */
public class Lc_220_存在重复元素_III {

    public static void main(String[] args) {
        Lc_220_存在重复元素_III lc = new Lc_220_存在重复元素_III();

        int[] nums = {1, 5, 9, 1, 5, 9};
        int k = 2, t = 3;

        System.out.println("整数数组" + Arrays.toString(nums) + "中" + (lc.containsNearbyAlmostDuplicate(nums, k, t) ? "存在" : "不存在") + "两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= " + t + " ，同时又满足 abs(i - j) <= " + k);
    }

    // 滑动窗口 + 二分 （TreeMap） - 时间复杂度 O(NlogK) - 空间复杂度 O(K)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;

        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long x = (long) nums[i];

            Long lo = ts.floor(x);
            Long hi = ts.ceiling(x);

            if (lo != null && x - lo <= t) return true;
            if (hi != null && hi - x <= t) return true;

            ts.add(x);
            if (i >= k) ts.remove((long) nums[i - k]);
        }

        return false;
    }

    // 桶排序 - 时间复杂度 O(N) - 空间复杂度 O(K)
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;

        // id = 桶id, b = 每个桶的大小
        long id = 0, b = (long) t + 1;
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long x = (long) nums[i];

            id = getId(x, b);

            // 查同一个桶
            if (map.containsKey(id)) return true;
            // 查相邻的桶
            if (map.containsKey(id - 1) && x - map.get(id - 1) <= t) return true;
            if (map.containsKey(id + 1) && map.get(id + 1) - x <= t) return true;

            map.put(id, x);  // 入桶
            if (i >= k) map.remove(getId(nums[i - k], b));  // 滑动窗口
        }

        return false;
    }

    private long getId(long x, long b) {
        return x >= 0 ? x / b : x / b - 1;
    }
}
