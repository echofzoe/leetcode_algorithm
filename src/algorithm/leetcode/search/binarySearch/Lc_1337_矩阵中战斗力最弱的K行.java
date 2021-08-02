package algorithm.leetcode.search.binarySearch;

import java.util.*;

/**
 * 矩阵中战斗力最弱的 K 行
 * <P>https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/</P>
 *
 * @author echofzoe
 * @since 2021.8.2
 */
public class Lc_1337_矩阵中战斗力最弱的K行 {

    public static void main(String[] args) {
        Lc_1337_矩阵中战斗力最弱的K行 lc = new Lc_1337_矩阵中战斗力最弱的K行();

        int[][] mat = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}};
        int k = 3;
        
        System.out.println("给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。\n" +
                "请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。\n" +
                "如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。\n" +
                "军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。\n");
        System.out.println("输入：mat = " + Arrays.deepToString(mat) + ", k = " + k);
        System.out.println("输出：" + Arrays.toString(lc.kWeakestRows(mat, k)));  // [1,2,3]
    }

    // 二分 + 小根堆
    // - 时间复杂度 O(MlogN+klogM) 需要O(MlogN)的时间对每一行进行二分查找，需要O(M)的时间建堆，需要O(klogM)的时间从堆中取出k个最小的元素
    // - 空间复杂度 O(M) M为mat的长度，N为mat[0]的长度
    public int[] kWeakestRows(int[][] mat, int k) {
        List<int[]> list = new ArrayList<>();
        int idx = 0;
        for (int[] nums : mat) {
            list.add(new int[] {helper(nums), idx++});
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int[] arr : list) {
            pq.offer(arr);
        }

        int[] res = new int[k];
        idx = 0;
        while (k-- > 0) {
            res[idx++] = pq.poll()[1];
        }

        return res;
    }

    private int helper(int[] nums) {
        if (nums[0] == 0) return 0;
        int l = 0, m, r = nums.length - 1;

        while (l < r) {
            m = l + (r - l + 1) / 2;

            if (nums[m] == 1) l = m;
            else r = m - 1;
        }

        return l + 1;
    }

}
