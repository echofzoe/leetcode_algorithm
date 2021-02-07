package algorithm.leetcode.slidingWindow;

import java.util.Arrays;

public class Lc_1423_可获得的最大点数 {

    // 可获得的最大点数
    // https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/

    public static void main(String[] args) {
        Lc_1423_可获得的最大点数 lc = new Lc_1423_可获得的最大点数();
        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int[] cardPoints2 = {2,2,2};
        int k1 = 3, k2 = 2;

        System.out.println("每次从" + Arrays.toString(cardPoints1) + "的头或尾抽一张卡牌，共抽三张，能获得的最大点数是" + lc.maxScore2(cardPoints1, k1));
//        System.out.println("每次从" + Arrays.toString(cardPoints2) + "的头或尾抽一张卡牌，共抽三张，能获得的最大点数是" + lc.maxScore2(cardPoints2, k2));
    }

    // 滑动窗口 - 滑取需要的点数 - 时间复杂度 O(K) - 空间复杂度 O(1)
    public int maxScore1(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[n - 1 - i];
            sum -= cardPoints[k - 1 - i];
            max = Math.max(max, sum);
        }

        return sum;
    }

    // 滑动窗口 - 滑取不需要的点数 - 时间复杂度 O(N) - 空间复杂度 O(N) 可取消前缀和数组以优化成O(1)
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + cardPoints[i];
        }

        int minSum = preSum[n - k] - preSum[0];
        for (int i = 1; i <= k; i++) {
            int cur = preSum[n - k + i] - preSum[i];
            minSum = Math.min(minSum, cur);
        }

        return preSum[n] - minSum;
    }

}
