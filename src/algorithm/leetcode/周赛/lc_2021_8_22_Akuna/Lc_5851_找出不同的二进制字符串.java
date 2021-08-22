package algorithm.leetcode.周赛.lc_2021_8_22_Akuna;

import java.util.Arrays;

/**
 * 找出不同的二进制字符串
 * <P>https://leetcode-cn.com/problems/find-unique-binary-string/</P>
 *
 * @author echofzoe
 * @since 2021.8.22
 */
public class Lc_5851_找出不同的二进制字符串 {

    public static void main(String[] args) {
        Lc_5851_找出不同的二进制字符串 lc = new Lc_5851_找出不同的二进制字符串();

        String[] nums = {"01","10"};

        System.out.println("给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。\n");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findDifferentBinaryString(nums));  // 其中一个符合的答案为 "11"
    }

    // 摩托对角线 - 时间复杂度 O(N) - 空间复杂度 O(1)
    /*
       反证法：
        假设构造串res的第i个字符和nums[i][i]不同，那么这个串就和所有的串都不同。
        那么如果res和nums中的一个串相同，即res==nums[i]，则res[i]==nums[i][i]，与假设矛盾。
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (nums[i].charAt(i) == '0') sb.append('1');
            else sb.append('0');
        }

        return sb.toString();
    }

}
