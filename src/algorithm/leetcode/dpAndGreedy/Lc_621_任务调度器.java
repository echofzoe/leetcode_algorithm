package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_621_任务调度器 {

    // 任务调度器
    // https://leetcode-cn.com/problems/task-scheduler/

    public static void main(String[] args) {
        Lc_621_任务调度器 lc = new Lc_621_任务调度器();
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;

        System.out.println("两个相同种类的任务之间必须有长度为整数 " + n + " 的冷却时间，则完成所有任务" + Arrays.toString(tasks) + "所需要的最短时间为 " + lc.leastInterval(tasks, n));
    }

    // 贪心 - 时间复杂度 O(∣task∣+∣Σ∣)，其中∣Σ∣是数组 task 中出现任务的种类，在本题中任务用大写字母表示，即不会超过26 - 空间复杂度 O(∣Σ∣)
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;

        int[] buckets = new int[26];
        for (char task : tasks) {
            buckets[task - 65]++;
        }
        Arrays.sort(buckets);

        int maxFreq = buckets[25];    // 任务出现的最大频次
        int maxCount = 1;    // 具有最大频次的任务个数
        for (int i = 25; i >= 1; i--) {
            if (buckets[i - 1] == buckets[i]) maxCount++;
            else break;
        }

        int res = (maxFreq - 1) * (n + 1) + maxCount;

        return Math.max(res, len);
    }

}
