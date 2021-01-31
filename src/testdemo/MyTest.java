package testdemo;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;

public class MyTest {

    public static void main(String[] args) {
        MyTest main = new MyTest();

        main.drawGraphics();

        int a = 1, b = 2, c = 3, d = 4;
        main.swap(a, b);
        main.swap(c, d);
    }

    private void drawGraphics() {
        for (int i = 7; i > 0; i -= 2) {

            int temp = i, space = 7 - temp;

            while (space > 0) {
                System.out.print(" ");
                space -= 2;
            }

            while (temp > 0) {
                System.out.print("*");
                temp--;
            }

            System.out.println();
        }
    }

    private void swap(int i1, int i2) {
        System.out.println("交换前 i1 = " + i1 + ", i2 = " + i2);

        if (i1 != i2) {
            i1 ^= i2;
            i2 ^= i1;
            i1 ^= i2;
        }

        System.out.println("交换后 i1 = " + i1 + ", i2 = " + i2);
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        System.out.println(BinaryTreeSerialize.serialize(root, 3));
    }

    @Test
    public void test2() {
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
    public void test3() {
        int[][] r = {{5, 8}, {3, 9}, {3, 12}};
        System.out.println(countGoodRectangles(r));
    }

    public int countGoodRectangles(int[][] rectangles) {
        for (int[] r : rectangles) {
            Arrays.sort(r);
        }

        System.out.println(Arrays.deepToString(rectangles));

        Arrays.sort(rectangles, (x, y) -> x[0] - y[0]);

        System.out.println(Arrays.deepToString(rectangles));

        int cur = 0, res = 0;
        for (int i = 0; i < rectangles.length; i++) {
            if (i == 0) {
                cur = 1;
                continue;
            }

            if (rectangles[i][0] == rectangles[i - 1][0]) {
                cur++;
                res = Math.max(res, cur);
            } else {
                cur = 1;
            }
        }

        return res;
    }

    @Test
    public void test4() {
//        System.out.println((10 ^ 9));
//        System.out.println((10 ^ 9 ^ 5));

        int[][] adjacentPairs = {{2, 1}, {3, 4}, {3, 2}};
        System.out.println(Arrays.toString(restoreArray(adjacentPairs)));
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        int[] res = new int[n + 1];

        for (int[] tmp : adjacentPairs) Arrays.sort(tmp);
        Arrays.sort(adjacentPairs, Comparator.comparingInt(x -> x[0]));

        int index = 0;
        res[index++] = adjacentPairs[0][0];
        res[index++] = adjacentPairs[0][1];

        int prev = adjacentPairs[0][1];
        for (int i = 0; i < n + 1; i++) {
            int idx = binarySearch(adjacentPairs, prev);

            if (idx != -1) {
                res[index++] = adjacentPairs[idx][1];
                prev = adjacentPairs[idx][1];
            }
        }

        return res;
    }

    private int binarySearch(int[][] adjacentPairs, int x) {
        int left = 0, mid, right = adjacentPairs.length - 1;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (adjacentPairs[mid][0] < x) {
                left = mid + 1;
            } else if (adjacentPairs[mid][0] > x) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


}
