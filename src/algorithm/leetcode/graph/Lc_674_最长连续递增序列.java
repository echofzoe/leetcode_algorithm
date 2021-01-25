package algorithm.leetcode.graph;

import algorithm.leetcode.utils.UnionFindSet;

import java.util.Arrays;

public class Lc_674_最长连续递增序列 {

    // 最长连续递增序列
    // https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/

    public static void main(String[] args) {
        Lc_674_最长连续递增序列 lc = new Lc_674_最长连续递增序列();
        int[] nums = {1, 3, 5, 4, 7};

        System.out.println(Arrays.toString(nums) + "中最长连续递增序列的长度为 " + lc.findLengthOfLCISUfs(nums));
    }

    // 并查集 - 时间复杂度 O() - 空间复杂度 O()
    public int findLengthOfLCISUfs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        UFS674 ufs = new UFS674(n);

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) ufs.union(i, i + 1);
        }

        return ufs.getMax();
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int findLengthOfLCISGreedy(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        int start = 0, end = 0, res = Integer.MIN_VALUE;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                end++;
            } else {
                res = Math.max(res, end - start + 1);
                start = i;
                end = i;
            }
        }

        return Math.max(res, end - start + 1);
    }

}

class UFS674 extends UnionFindSet {

    private int n;

    public UFS674(int n) {
        super(n);
        this.n = getN();
    }

    public int getMax() {
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            ++cnt[find(i)];
        }

        return Arrays.stream(cnt).max().getAsInt();
    }

}
