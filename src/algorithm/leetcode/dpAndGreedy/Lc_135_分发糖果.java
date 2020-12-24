package algorithm.leetcode.dpAndGreedy;

import java.util.Arrays;

public class Lc_135_分发糖果 {

    // 分发糖果
    // https://leetcode-cn.com/problems/candy/

    public static void main(String[] args) {
        Lc_135_分发糖果 lc = new Lc_135_分发糖果();
        int[] input1 = {1, 0, 2};
        int[] input2 = {1, 2, 2};

        System.out.println("至少要为评分为" + Arrays.toString(input1) + "的孩子们准备" + lc.candy(input1) + "颗糖果");
        System.out.println("至少要为评分为" + Arrays.toString(input2) + "的孩子们准备" + lc.candy(input2) + "颗糖果");
    }

    // 贪心 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int candy(int[] ratings) {
        int n = ratings.length;

        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1; i < n; i++) if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;

        // int res = Math.max(left[n - 1], right[n - 1]);
        int res = left[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            res += Math.max(left[i], right[i]);
        }

        return res;
    }

}
