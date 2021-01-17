package algorithm.leetcode.周赛.lc_2021_1_17_ThoughtWorks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc_5653_可以形成最大正方形的矩形数目 {

    // 可以形成最大正方形的矩形数目
    // https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square/

    public static void main(String[] args) {
        Lc_5653_可以形成最大正方形的矩形数目 lc = new Lc_5653_可以形成最大正方形的矩形数目();
        int[][] rectangles = {{5, 8}, {3, 9}, {5, 12}, {16, 5}};

        System.out.println("设 maxLen 为可以从矩形数组中切分得到的最大正方形的边长，则" + Arrays.deepToString(rectangles) + "中可以切出边长为 maxLen 的正方形的矩形数目是" + lc.countGoodRectangles(rectangles));
    }

    // 模拟 - 时间复杂度 O(N) - 空间复杂度 O(N)
    public int countGoodRectangles(int[][] rectangles) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int maxLen = 0;
        
        for (int[] rectangle : rectangles) {
            int cur = Math.min(rectangle[0], rectangle[1]);
            cnt.put(cur, cnt.getOrDefault(cur, 0) + 1);
            maxLen = Math.max(maxLen, cur);
        }

        return cnt.get(maxLen);
    }

}
