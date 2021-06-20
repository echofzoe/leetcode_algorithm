package testdemo;

import org.junit.Test;

import java.util.*;

public class MyTest {

    public static void main(String[] args) {
        MyTest main = new MyTest();
    }

    @Test
    public void test1() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            set1.add(i);
            set2.add(i + 6);
        }
        set1.addAll(set2);
        System.out.println(set1);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> list3 = Collections.synchronizedList(new ArrayList<>());
    }

    @Test
    public void test2() {
        int tmp = 0b10101;
        int a = tmp;

        while (a > 0) {
            System.out.print(Integer.toBinaryString(a - 1) + " - ");
            a = (a - 1) & tmp;
            System.out.println(Integer.toBinaryString(a));
        }
    }

    @Test
    public void test3() {
        int a = -3;
        System.out.println(Integer.toBinaryString(a));

        for (int i = 0; i < 1; i++) {
            a >>= 1;
        }
        System.out.println(Integer.toBinaryString(a));

        // ------
        System.out.println(Integer.toBinaryString((-1 << 11)));
    }

    @Test
    public void test4() {
        long x = 0x40000d24;
//        long x = 0b00001000011000000000000000000000;
        System.out.println(Long.toBinaryString(x));
//        System.out.println(Long.toHexString(x));

        long a = 0b1000000000000000000110101000000, b = 0b1000000000000000000110100100100;
        System.out.println(Long.toString(a, 10) + " " + Long.toString(b, 10) + " " + Long.toString(a - b, 10));
    }

//    @Test
//    public void test5() {
//        byte stepDrop = 4;
//        Point start = new Point(0, 0);
//        Point end = new Point(1, 1);
//        List<List<Point>> paths = findPath(map, start, end, stepDrop);
//        for (List<Point> path : paths) {
//            System.out.println(path);
//        }
//    }

    /**
     * 查找最优路径
     *
     * @param map      地形数据
     * @param start    起始点
     * @param end      结束点
     * @param stepDrop 单步落差
     * @return
     */
//    public static List<List<Point>> findPath(byte[][] map, Point start, Point end, byte stepDrop) {
//        m = map.length;
//        n = map[0].length;
//        memo = new boolean[m][n];
//        res = new LinkedList<>();
//        tmp = new LinkedList<>();
//
//        dfs(start, end, 0);
//
//        return res;
//    }

//    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//    private static boolean[][] memo;
//    private static List<List<Point>> res;
//    private static List<Point> tmp;
//    private static int m, n;
//
//    private static void dfs(Point cur, Point end, int idx) {
//        int x = cur.x, y = cur.y;
//        if (x == end.x && y == end.y) {
//            tmp.add(end);
//            res.add(new ArrayList<>(tmp));
//            tmp.remove(tmp.size() - 1);
//            return;
//        }
//
//        if (x < 0 || y < 0 || x >= m || y >= n || memo[x][y]) return;
//
//        memo[x][y] = true;
//        tmp.add(cur);
//
//        for (int[] dir : dirs) {
//            int x1 = x + dir[0], y1 = y + dir[1];
//            dfs(new Point(x1, y1), end, idx + 1);
//        }
//
//        // backtrace
//        tmp.remove(idx);
//        memo[x][y] = false;
//    }
//
//    static byte[][] map =
//            {
//                    {11, 12, 15},
//                    {16, 20, 20},
//                    {15, 20, 29}
//            };
//
//    static class Point {
//        int x;
//        int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public String toString() {
//            return "(" + x + "," + y + ")";
//        }
//    }
    @Test
    public void test6() {
        int[][] grid1 = {{0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 0}};
        int[][] grid2 = {{1, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0}};

        System.out.println(countSubIslands(grid1, grid2));
    }

    private boolean[][] vis;
    private int res, m, n;
    private int[][] grid1, grid2;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        m = grid1.length;
        n = grid1[0].length;
        res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] > 0) {
                    if (watch(i, j)) {
                        res++;
                    }

                }
            }
        }

        return res;
    }

    public boolean watch(int row, int col) {
        if (row >= m || col >= n || row < 0 || col < 0) {
            return true;
        } else if (grid1[row][col] == 0) {
            grid2[row][col] = -1;
            return grid2[row][col] == 0;
        } else if (grid2[row][col] <= 0) {
            return true;
        } else {
            grid2[row][col] = -1;
            return watch(row, col + 1)
                    && watch(row + 1, col)
                    && watch(row - 1, col)
                    && watch(row, col - 1);
        }
    }

}
