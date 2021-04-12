package algorithm.leetcode.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 最大数
 * https://leetcode-cn.com/problems/largest-number/
 *
 * @author echofzoe
 * @since 2021.4.12
 */
public class Lc_179_最大数 {

    public static void main(String[] args) {
        Lc_179_最大数 lc = new Lc_179_最大数();

        int[] nums = {3, 30, 34, 5, 9};

        System.out.println("重新排列" + Arrays.toString(nums) + "中每个数的顺序能够得到的最大的整数是" + lc.largestNumber(nums));
    }

    public String largestNumber(int[] nums) {
        int n = nums.length;

        Map<String, Integer> cnt = new HashMap<>();

        int t = 0;
        for (int i : nums) cnt.put(String.valueOf(i), cnt.getOrDefault(i, 0) + 1);

        

    }

}
