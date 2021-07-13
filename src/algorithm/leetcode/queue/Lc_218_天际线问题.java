package algorithm.leetcode.queue;

import java.util.*;

/**
 * 天际线问题
 * <P>https://leetcode-cn.com/problems/the-skyline-problem/</P>
 *
 * @author echofzoe
 * @since 2021.7.13
 */
public class Lc_218_天际线问题 {

    public static void main(String[] args) {
        Lc_218_天际线问题 lc = new Lc_218_天际线问题();

        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        System.out.println("输入：buildings = " + Arrays.deepToString(buildings));
        System.out.println("输出：" + lc.getSkyline(buildings));  // [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        List<List<Integer>> res = new ArrayList<>();

        // 预处理
        List<int[]> list = new ArrayList<>();
        for (int[] b : buildings) {
            int l = b[0], r = b[1], h = b[2];
            list.add(new int[]{l, -h});
            list.add(new int[]{r, h});
        }

        Collections.sort(list, (a, b) -> {
            if () return a[0] != b[0] ? a[0] - b[0] : ;

        });

        int prev = 0;


        return res;
    }

}
