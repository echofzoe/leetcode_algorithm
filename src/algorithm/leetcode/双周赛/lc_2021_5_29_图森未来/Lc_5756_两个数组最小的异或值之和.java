package algorithm.leetcode.双周赛.lc_2021_5_29_图森未来;

import java.util.Arrays;

/**
 * 两个数组最小的异或值之和
 * <P>https://leetcode-cn.com/problems/minimum-xor-sum-of-two-arrays/</P>
 *
 * @author echofzoe
 * @since 2021.5.30
 */
public class Lc_5756_两个数组最小的异或值之和 {

    public static void main(String[] args) {
        Lc_5756_两个数组最小的异或值之和 lc = new Lc_5756_两个数组最小的异或值之和();

        int[] nums1 = {1, 0, 3}, nums2 = {5, 3, 4};
        
        System.out.println("给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。\n" +
                "两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) （下标从 0 开始）。\n" +
                "  - 比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。\n" +
                "请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。\n" +
                "请你返回重新排列之后的 异或值之和。");
        System.out.println("输入：nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("输出：" + lc.minimumXORSum(nums1, nums2));
    }

    public int minimumXORSum(int[] nums1, int[] nums2) {

    }

}
