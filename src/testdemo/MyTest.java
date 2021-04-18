package testdemo;

import algorithm.leetcode.utils.BinaryTreeSerialize;
import algorithm.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    }

    @Test
    public void test4() {
//        System.out.println(Integer.toBinaryString((-1 << 11)));
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        System.out.println(Arrays.toString(getOrder(tasks)));
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        Arrays.sort(tasks, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });

        int max = tasks[n - 1][0];
        Deque<Integer> ready = new LinkedList<>();

        int idx = 0;
        for (int i = 1; i <= max; ) {
            while (tasks[idx][0] == i) {
                ready.add(idx);
                idx++;

                if (idx == n) break;
            }
            i++;
        }

        int[] res = new int[n];
        for (int i = 0; i < ready.size(); i++) {
            res[i] = ready.pollFirst();
        }

        return res;
    }

}
