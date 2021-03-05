package algorithm.leetcode.search.binarySearch;

import java.util.Arrays;

public class Lc_354_俄罗斯套娃信封问题 {

    // 俄罗斯套娃信封问题
    // - 此问题是第300题LIS在二维的拓展
    // https://leetcode-cn.com/problems/russian-doll-envelopes/

    public static void main(String[] args) {
        Lc_354_俄罗斯套娃信封问题 lc = new Lc_354_俄罗斯套娃信封问题();
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};

        System.out.println("在一堆信封" + Arrays.deepToString(envelopes) + "中，最多能\"套娃\"" + lc.maxEnvelopes(envelopes) + "个信封");
    }

    // 贪心 + 二分 - 时间复杂度 O(NlogN) - 空间复杂度 O(N)
    // - 预处理二维数组，再转化成一维的LIS问题
    // - 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return 0;

        Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);

        int[] d = new int[n];
        int idx = 0;
        d[idx] = envelopes[0][1];
        for (int i = 1; i < n; i++) {
            int cur = envelopes[i][1];

            if (d[idx] < cur) {
                d[++idx] = cur;
            } else {
                int lo = 0, mid, hi = idx, pos = -1;
                while (lo <= hi) {
                    mid = lo + (hi - lo) / 2;
                    if (d[mid] < cur) {
                        pos = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }

                d[pos + 1] = cur;
            }
        }

        return idx + 1;
    }

    // DP - 时间复杂度 O(N^2) - 空间复杂度 O(N)
    // - 预处理二维数组，再转化成一维的LIS问题
    // - 定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取
    // - 状态转移方程：dp[i] = max(dp[x]) + 1, 0 <= x < i 且 nums[x] < nums[i]
    public int maxEnvelopesDP(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return 0;

        Arrays.sort(envelopes, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

}
