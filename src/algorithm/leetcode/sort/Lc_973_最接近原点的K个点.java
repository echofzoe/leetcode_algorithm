package algorithm.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Lc_973_最接近原点的K个点 {

    // 最接近原点的 K 个点
    // https://leetcode-cn.com/problems/k-closest-points-to-origin/

    public static void main(String[] args) {
        Lc_973_最接近原点的K个点 lc = new Lc_973_最接近原点的K个点();
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;

        System.out.println("列表" + Arrays.deepToString(points1) + "中距离原点最近的" + k1 + "个点是" + Arrays.deepToString(lc.kClosest(points1, k1)));
        System.out.println("列表" + Arrays.deepToString(points2) + "中距离原点最近的" + k2 + "个点是" + Arrays.deepToString(lc.kClosestQuicklySort(points2, k2)));
        System.out.println("列表" + Arrays.deepToString(points2) + "中距离原点最近的" + k2 + "个点是" + Arrays.deepToString(lc.kClosestHeap(points2, k2)));
    }

    // 直接排序 - 时间复杂度 O(N*logN) N为points数组的长度 logN为排序的算法复杂度 - 空间复杂度 O(logN) 为排序所需的额外空间
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (point1, point2) -> {
            return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
        });

        return Arrays.copyOfRange(points, 0, K);
    }

    // 快速排序
    // - 平均情况下 时间复杂度 O(N) N为points数组的长度 - 空间复杂度 O(logN) 为递归栈的开销
    // - 最坏情况下
    // -- 时间复杂度 O(N^2) 每次的划分点都是最大值或最小值 一共需要划分n−1次 而一次划分需要线性的时间复杂度
    // -- 空间复杂度 O(N) 最坏情况下 需要划分n−1次 对应递归的深度为n−1层
    public int[][] kClosestQuicklySort(int[][] points, int K) {
        int left = 0, right = points.length - 1;

        while (left < right) {
            int index = quicklySort(points, left, right);
            if (index == K) break;
            else if (index < K) left = index + 1;
            else right = index - 1;
        }

        return Arrays.copyOfRange(points, 0, K);
    }

    private int quicklySort(int[][] points, int left, int right) {
        int i = left, j = right + 1;    // j+1是为了适应下面while循环里的第一次points[--j][0]
        int pivot = dist(points[i][0], points[i][1]);
        while (true) {
            while (j > left && dist(points[--j][0], points[j][1]) > pivot) ;
            while (i < right && dist(points[++i][0], points[i][1]) < pivot) ;
            if (i >= j) break;
            swap(points, i, j);
        }
        swap(points, left, j);
        return j;
    }

    private int dist(int i, int j) {
        return i * i + j * j;
    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    // 大根堆
    // - 时间复杂度 O(N*logK) N是数组points的长度 由于优先队列维护的是前K个距离最小的点 因此弹出和插入操作的单次时间复杂度均为O(logK)
    // - 空间复杂度 O(K) 因为优先队列中最多有K个点
    public int[][] kClosestHeap(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < K; i++) {
            pq.offer(new int[]{dist(points[i][0], points[i][1]), i});
        }

        for (int i = K; i < points.length; i++) {
            int dist = dist(points[i][0], points[i][1]);
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[pq.poll()[1]];
        }

        return res;
    }

}
