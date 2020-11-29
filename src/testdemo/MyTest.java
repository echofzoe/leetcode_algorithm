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
}
