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
        int[] apples = {3,1,1,0,0,2};
        int[] days = {3,1,1,0,0,2};
        System.out.println(eatenApples(apples, days));
    }

    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;

        int len = 0;
        for (int i = 0; i < n; i++) {
            len = Math.max(len, i + days[i]);
        }

        int[] expire = new int[len];

        int res = 0;

        int store = 0;

        for (int i = 0; i < n; i++) {

            if (apples[i] > 0) {
                store += apples[i];
                expire[i + days[i] - 1] += apples[i];
            }

            if (store > 0) {
                store--;
                res++;
            }


            for (int j = 0; j < len; j++) {
                if (expire[j] > 0) {
                    expire[j]--;
                    break;
                }
            }

            store = Math.max(store - expire[i], 0);
            expire[i] = 0;
        }

        int tmp = res;
        for (int i = n; i < len; i++) {
            if (store > 0) {
                store--;
                res++;
            }

            if (expire[i] > 0) {
                expire[i] -= res - tmp;
                tmp = res;
            }

            store = Math.max(store - expire[i], 0);
        }

        return res;
    }

    public int maxResult(int[] nums, int k) {
        int n = nums.length, res = nums[0];

        int index = 1;
        while (index < n) {
            int max = Integer.MIN_VALUE, maxIndex = 0;

            for (int i = 0; i < k && index + i < n; i++) {
                if (nums[index + i] >= max) {
                    max = nums[index + i];
                    maxIndex = index + i;
                }
            }

            res += nums[maxIndex];

            index = maxIndex + 1;
        }

        return res;
    }

    @Test
    public void test4() {
        String s = "dog cat cat fish";
        String[] arr = s.split(" ");
        System.out.println(Arrays.toString(arr));
    }

}
