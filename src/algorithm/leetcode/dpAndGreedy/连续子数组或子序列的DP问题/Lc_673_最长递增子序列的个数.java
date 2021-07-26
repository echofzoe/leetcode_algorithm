package algorithm.leetcode.dpAndGreedy.连续子数组或子序列的DP问题;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * <P>https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/</P>
 *
 * @author echofzoe
 * @since 2021.7.26
 */
public class Lc_673_最长递增子序列的个数 {

    public static void main(String[] args) {
        Lc_673_最长递增子序列的个数 lc = new Lc_673_最长递增子序列的个数();

        int[] nums = {1, 3, 5, 4, 7};

        System.out.println("给定一个未排序的整数数组，找到最长递增子序列的个数。");
        System.out.println("输入：nums = " + Arrays.toString(nums));
        System.out.println("输出：" + lc.findNumberOfLIS(nums));  // 2
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        // lens[i]表示nums[0:i]的最长递增子序列的长度
        // cnts[i]表示nums[0:i]中具有lens[i]长度的最长递增子序列的个数
        int[] lens = new int[n], cnts = new int[n];
        // base case
        Arrays.fill(cnts, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (lens[j] >= lens[i]) {
                        lens[i] = lens[j] + 1;
                        cnts[i] = cnts[j];
                    } else if (lens[j] + 1 == lens[i]) {
                        cnts[i] += cnts[j];
                    }
                }
            }
        }

        int longest = 0, res = 0;
        for (int len : lens) {
            longest = Math.max(longest, len);
        }
        for (int i = 0; i < n; i++) {
            if (lens[i] == longest) {
                res += cnts[i];
            }
        }

        return res;
    }

}
