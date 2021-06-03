package algorithm.leetcode.array.prefixAndSuffix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 连续数组
 * <P>https://leetcode-cn.com/problems/contiguous-array/</P>
 *
 * @author echofzoe
 * @since 2021.6.3
 */
public class Lc_525_连续数组 {

    public static void main(String[] args) {
        Lc_525_连续数组 lc = new Lc_525_连续数组();

        int[] nums = {0, 1, 0};

        System.out.println("给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findMaxLength(nums));  // 2
    }

    // 前缀和 + 哈希表 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int findMaxLength(int[] nums) {
        int n = nums.length;

        int res = 0, cnt = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) cnt++;
            else cnt--;

            if (map.containsKey(cnt)) {
                int preIdx = map.get(cnt);
                res = Math.max(res, i - preIdx);
            } else {
                map.put(cnt, i);
            }
        }

        return res;
    }

}
