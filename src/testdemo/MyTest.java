package testdemo;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;
import org.junit.Test;

public class MyTest {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        System.out.println(BinaryTreeSerialize.serialize(root, 3));
    }

}
