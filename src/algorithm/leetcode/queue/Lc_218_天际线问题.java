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

    // 扫描线 - 时间复杂度 O(N^2) - 空间复杂度 O(N)
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
        // 先严格按照横坐标进行「从小到大」排序
        // 对于某个横坐标而言，可能会同时出现多个点，应当按照如下规则进行处理：
        // - 优先处理左端点，再处理右端点
        // - 如果同样都是左端点，则按照高度「从大到小」进行处理（将高度增加到优先队列中）
        // - 如果同样都是右端点，则按照高度「从小到大」进行处理（将高度从优先队列中删掉）
        list.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // 大根堆
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int preHeight = 0;
        q.offer(preHeight);

        for (int[] arr : list) {
            int point = arr[0], height = arr[1];

            // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
            if (height < 0) q.offer(-height);
                // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
            else q.remove(height);

            // 取出最高高度，如果其不与前一矩形“上边”延展而来的那些边重合，则可以被记录
            int highest = q.peek();
            if (highest != preHeight) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(point);
                tmp.add(highest);
                res.add(tmp);
                preHeight = highest;
            }
        }

        return res;
    }

}
