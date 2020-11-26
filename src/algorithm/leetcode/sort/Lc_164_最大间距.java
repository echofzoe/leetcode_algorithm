package algorithm.leetcode.sort;


import java.util.Arrays;

public class Lc_164_最大间距 {

    // 最大间距
    // https://leetcode-cn.com/problems/maximum-gap/

    public static void main(String[] args) {
        Lc_164_最大间距 lc = new Lc_164_最大间距();
        int[] nums = {3, 6, 2, 5, 4, 19, 1};
//        int[] nums = {1, 100000};

        System.out.println("无序数组" + Arrays.toString(nums) + "在排序后，相邻元素之间最大的差值是" + lc.maximumGapBucketSort(nums));
    }

    // API(quicksort) - 时间复杂度 O() - 空间复杂度 O()
    public int maximumGapBF(int[] nums) {
        Arrays.sort(nums);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }

        return res;
    }

    // 桶排序 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int maximumGapBucketSort(int[] nums) {
        int n = nums.length;
        // 特判
        if (n < 2) return 0;

        // 边界 + 特判
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        if (max == min) return 0;

        // 计算间隔 - 间隔不为0
        int interval = (max - min) / (n - 1);
        interval = Math.max(1, interval);

        // 计算桶的大小
        int bucketSize = (max - min) / interval + 1;

        // 桶初始化
        int[] maxBucket = new int[bucketSize];
        int[] minBucket = new int[bucketSize];
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        Arrays.fill(minBucket, Integer.MAX_VALUE);

        // 更新桶元素
        for (int num : nums) {
            // idx - 每一个值对应的桶的索引
            int idx = (num - min) / interval;
            maxBucket[idx] = Math.max(maxBucket[idx], num);
            minBucket[idx] = Math.min(minBucket[idx], num);
        }

        int res = 0;
        int preMax = min;
        for (int i = 0; i < bucketSize; i++) {
            if (maxBucket[i] == Integer.MIN_VALUE) continue;  // 空桶
            res = Math.max(res, minBucket[i] - preMax);
            preMax = maxBucket[i];
        }

        res = Math.max(res, max - preMax);

        return res;
    }

}
