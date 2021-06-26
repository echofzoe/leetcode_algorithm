package testdemo;

import org.junit.Test;

import java.sql.Timestamp;
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

        // 获取当前时间
        System.out.println(formatTimeYMDHMSF(System.currentTimeMillis()));
    }

    private String formatTimeYMDHMSF(long time) {
        Timestamp ts = new Timestamp(time);
        String timeStr = ts.toString();
        System.out.println(timeStr);
        String[] array = timeStr.split(" ");
        if (array.length == 2) {
            return array[1];
        } else {
            return array[0];
        }
    }

    @Test
    public void test5() {
    }

    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;

        // 第一个栈模拟 "pre >= cur 时，删除 pre" 的情况
        Deque<Integer> dq1 = new LinkedList<>() {{
            offerLast(nums[0]);
        }};

        // 第二个栈模拟 "pre >= cur 时，删除 cur" 的情况
        Deque<Integer> dq2 = new LinkedList<>() {{
            offerLast(nums[0]);
        }};

        // cnt1、cnt2 各自记录栈1、栈2模拟情况下删除的次数
        // 删除次数>1时，已经不符合题意，直接返回false即可
        int cnt1 = 0, cnt2 = 0;

        for (int i = 1; i < n; i++) {
            int x = nums[i];

            while (!dq1.isEmpty() && x <= dq1.peekLast()) {
                dq1.pollLast();
                cnt1++;
            }
            dq1.offerLast(x);

            if (dq2.isEmpty() || x > dq2.peekLast()) {
                dq2.offerLast(x);
            } else {
                cnt2++;
            }

            if (cnt1 > 1 && cnt2 > 1) return false;
        }

        return true;
    }

}
