package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 蛇梯棋
 * <P>https://leetcode-cn.com/problems/snakes-and-ladders/</P>
 *
 * @author echofzoe
 * @since 2021.6.29
 */
public class Lc_909_蛇梯棋 {

    public static void main(String[] args) {
        Lc_909_蛇梯棋 lc = new Lc_909_蛇梯棋();

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};

        System.out.println("输入：board = " + Arrays.deepToString(board));
        System.out.println("输出：" + lc.snakesAndLadders(board));  // 4
    }

    // bfs - 时间复杂度 O(N^2) - 空间复杂度 O(N^2)
    private int n;
    private int[] nums;

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        if (board[n - 1][0] != -1 || board[0][0] != -1) return -1;

        // 将二维数组扁平化成一维数组，方便后续计算
        nums = new int[n * n + 1];
        boolean l2r = true;
        for (int i = n - 1, idx = 1; i >= 0; i--) {
            for (int j = (l2r ? 0 : n - 1); l2r ? j < n : j >= 0; j += l2r ? 1 : -1) {
                nums[idx++] = board[i][j];
            }
            l2r = !l2r;
        }

        return bfs();
    }

    private int bfs() {
        Deque<Integer> d = new ArrayDeque<>() {{
            add(1);
        }};

        Map<Integer, Integer> m = new HashMap<>() {{
            put(1, 0);
        }};

        while (!d.isEmpty()) {
            int size = d.size();

            for (int i = 0; i < size; i++) {
                int cur = d.poll();
                int step = m.get(cur);

                // exit
                if (cur == n * n) return step;

                for (int j = 1; j <= 6; j++) {
                    int next = cur + j;

                    if (next > n * n) continue;  // 越界，剪枝
                    if (nums[next] != -1) next = nums[next];  // 遇蛇梯，再进一步

                    if (m.containsKey(next)) continue;

                    m.put(next, step + 1);
                    d.add(next);
                }
            }
        }

        return -1;
    }

}
