package algorithm.leetcode.string;

import java.util.Arrays;

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

        String[] ss = new String[n];
        for (int i = 0; i < n; i++) ss[i] = "" + nums[i];

        Arrays.sort(ss, (a, b) -> {
            String sa = a + b, sb = b + a;
            return sb.compareTo(sa);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : ss) sb.append(s);

        // 去除前导0
        int k = 0;
        while (k < n - 1 && sb.charAt(k) == '0') k++;

        return sb.substring(k);
    }

}
