package testdemo;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;
import org.hamcrest.core.Is;
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

    }

    public boolean closeStrings_京东11月15日5602题(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            if (m1.containsKey(c)) {
                m1.put(c, m1.get(c) + 1);
            } else {
                m1.put(c, 1);
            }
        }

        for (char c : word2.toCharArray()) {
            if (m2.containsKey(c)) {
                m2.put(c, m2.get(c) + 1);
            } else {
                m2.put(c, 1);
            }
        }

        System.out.println(m1.toString() + "---" + m2.toString());

        Queue<Integer> q1 = new PriorityQueue<>();
        Queue<Integer> q2 = new PriorityQueue<>();

        for (char c : word1.toCharArray()) {
            if (!m2.containsKey(c)) return false;

            if (!m2.get(c).equals(m1.get(c))) {
                if (!q1.contains(m1.get(c))) q1.add(m1.get(c));
                if (!q2.contains(m2.get(c))) q2.add(m2.get(c));;
            }
        }

        System.out.println(Arrays.toString(q1.toArray()) + "---" + Arrays.toString(q2.toArray()));
        while (!q1.isEmpty()) {
            if (!q1.poll().equals(q2.poll())) return false;
        }

        return true;
    }


}
