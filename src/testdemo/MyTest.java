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
        String s1 = "035985750011523523129774573439111590559325a1554234973";
        String s2 = "a1b01c001";
        System.out.println(numDifferentIntegers(s2));
    }

    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();

        char[] cs = word.toCharArray();
        int n = cs.length;

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < n) {
            if (isNum(cs[idx])) {
                sb.delete(0, sb.length());
                while (idx < n && isNum(cs[idx])) {
                    sb.append(cs[idx++]);
                }

                while (sb.charAt(0) == '0' && sb.length() > 1) {
                    sb.deleteCharAt(0);
                }

                set.add(sb.toString());
            } else {
                idx++;
            }
        }

        return set.size();
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

}
