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
//        int[] nums = {1, 2, 4};
//        int k = 5;
//        System.out.println(maxFrequency(nums, k));
    }

//    public int maxFrequency(int[] nums, int k) {
//        int n = nums.length, res = 0;
//        Arrays.sort(nums);
//
//        int[][] diff = new int[n][2];
//        int[] cnt = new int[nums[n - 1] + 1];
//        cnt[nums[0]]++;
//        for (int i = 1; i < n; i++) {
//            diff[i - 1][0] = i - 1;
//            diff[i - 1][1] = nums[i] - nums[i - 1];
//
//            cnt[nums[i]]++;
//        }
//        Arrays.sort(diff, Comparator.comparingInt(a -> a[1]));
//
//        int idx = 1;
//        while (k > 0) {
//            while (k > 0) {
//                int a = diff[idx][0], b = diff[idx][1];
//                if (k < b) break;
//
//                cnt[nums[a]]--;
//                cnt[nums[a] + 1]++;
//
//                if (idx > 1) {
//                    diff[idx - 1][1] = nums[]
//                }
//
//                k -= diff[idx++][1];
//            }
//
//            idx = 1;
//        }
//
//        return Arrays.stream(cnt).max().getAsInt();
//    }

}
