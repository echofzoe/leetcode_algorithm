package algorithm.leetcode.周赛.lc_2021_3_21_贝壳;

import java.util.Map;
import java.util.logging.Level;

public class Lc_5711_有界数组中指定下标处的最大值 {

    // 有界数组中指定下标处的最大值
    // https://leetcode-cn.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/

    public static void main(String[] args) {
        Lc_5711_有界数组中指定下标处的最大值 lc = new Lc_5711_有界数组中指定下标处的最大值();
        int n = 3, index = 2, maxSum = 18;

        System.out.println("长度为" + n + "的数组中，在满足数组中所有元素之和不超过" + maxSum + "且相邻元素绝对值之差不超过1的情况下，index为" + index + "的位置能够取得的最大值是" + lc.maxValue1(n, index, maxSum));
    }

    // 双指针 - 时间复杂度 O(N) - 空间复杂度 O(1)
    public int maxValue(int n, int index, int maxSum) {

        int lo = index, hi = index, res = 1;
        maxSum -= n;

        while (lo > 0 || hi < n - 1) {
            int len = hi - lo + 1;

            if (maxSum >= len) {
                maxSum -= len;
                res++;

                lo = Math.max(0, lo - 1);
                hi = Math.min(n - 1, hi + 1);
            } else {
                break;
            }
        }

        res += maxSum / n;
        return res;
    }
    
    // 二分 - 时间复杂度 O(logM) 其中M为最大总和 - 空间复杂度 O(1)
    public int maxValue1(int n, int index, int maxSum) {

        int lo = 1, hi = maxSum - (n - 1);
        int leftLen = index, rightLen = n - 1 - index;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long leftMin = calcMin(leftLen, mid - 1);
            long rightMin = calcMin(rightLen, mid - 1);
            if (leftMin + rightMin + mid > maxSum) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi;
    }

    private long calcMin(long len, long fill) {
        if (len >= fill) {
            // len 填不满，序列为 [1, 1, 1, 2, 3, ...]，等差数列首项为 1
            // eg: [1, 1, 1, 2, 3]
            // 求和公式：(前 len - fill 项全 1) + (1 到 fill 的等差数列和)
            return (len - fill) + fill * (fill + 1) / 2;
        }

        // len 填得满，等差数列首项为 fill - len + 1
        // 等差数列求和公式（首项为a1）：a1 * n + [n * (n - 1) * d] / 2
        return (fill - len + 1) * len + len * (len - 1) / 2;
    }

}
