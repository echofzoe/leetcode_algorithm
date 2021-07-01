package algorithm.leetcode.dfsAndBfs.bfs;

import java.util.*;

/**
 * 滑动谜题
 * <P>https://leetcode-cn.com/problems/sliding-puzzle/</P>
 *
 * @author echofzoe
 * @since 2021.7.1
 */
public class Lc_773_滑动谜题 {

    public static void main(String[] args) {
        Lc_773_滑动谜题 lc = new Lc_773_滑动谜题();

        int[][] board = {{4, 1, 2}, {5, 0, 3}};

        System.out.println("在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.\n" +
                "一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.\n" +
                "最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。\n" +
                "给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。\n");
        System.out.println("输入：board = " + Arrays.deepToString(board));
        System.out.println("输出：" + lc.slidingPuzzle(board));
    }

    // bfs - 时间复杂度 O((mn)!⋅mn) - 空间复杂度 O((mn)!⋅mn)
    String s, end;
    int x, y;

    public int slidingPuzzle(int[][] board) {
        end = "123450";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        s = sb.toString();

        return bfs();
    }

    private int bfs() {
        Node root = new Node(s, x, y);
        Deque<Node> d = new LinkedList<>() {{
            add(root);
        }};
        Map<String, Integer> m = new HashMap<>() {{
            put(s, 0);
        }};

        while (!d.isEmpty()) {
            int size = d.size();

            while (size-- > 0) {
                Node cur = d.poll();
                int step = m.get(cur.s);

                // exit
                if (cur.s.equals(end)) return step;

                x = cur.x;
                y = cur.y;
                for (int[] dir : dirs) {
                    int x1 = x + dir[0], y1 = y + dir[1];
                    if (x1 < 0 || x1 >= 2 || y1 < 0 || y1 >= 3) continue;

                    String next = getNextStr(cur.s, x, y, x1, y1);

                    if (m.containsKey(next)) continue;
                    m.put(next, step + 1);

                    d.add(new Node(next, x1, y1));
                }
            }
        }

        return -1;
    }

    private String getNextStr(String s, int x, int y, int x1, int y1) {
        char[] cs = s.toCharArray();

        char c = cs[x * 3 + y];
        cs[x * 3 + y] = cs[x1 * 3 + y1];
        cs[x1 * 3 + y1] = c;

        return new String(cs);
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private class Node {
        String s;
        int x, y;

        Node(String _s, int _x, int _y) {
            s = _s;
            x = _x;
            y = _y;
        }
    }

}
