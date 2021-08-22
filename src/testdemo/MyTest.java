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
        String s = "1A";
        int x = 1000;
        System.out.println("16进制 0x" + s + " == 10进制 " + calc16To10(s));
        System.out.println("10进制 " + x + " == 16进制 0x" + calc10To16(x));

        s = "RS";
        x = 988;
        System.out.println("32进制 " + s + " == 10进制 " + calc32To10(s));
        System.out.println("10进制 " + x + " == 32进制 " + calc10To32(x));
    }

    private int calc16To10(String s) {
        int n = s.length(), res = 0;

        Map<Character, Integer> m = new HashMap<>() {{
            put('A', 10);
            put('B', 11);
            put('C', 12);
            put('D', 13);
            put('E', 14);
            put('F', 15);
        }};

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c < 'A') {
                res += (c - '0') * Math.pow(16, n - 1 - i);
            } else {
                res += m.get(c) * Math.pow(16, n - 1 - i);
            }
        }

        return res;
    }

    private String calc10To16(int x) {
        StringBuilder res = new StringBuilder();

        Map<Integer, Character> m = new HashMap<>() {{
            put(10, 'A');
            put(11, 'B');
            put(12, 'C');
            put(13, 'D');
            put(14, 'E');
            put(15, 'F');
        }};

        while (x > 0) {
            int t = x % 16;
            if (t < 10) {
                res.append((char) (t + '0'));
            } else {
                res.append(m.get(t));
            }

            x /= 16;
        }

        return res.reverse().toString();
    }

    private int calc32To10(String s) {
        int n = s.length(), res = 0;

        Map<Character, Integer> m = new HashMap<>() {{
            put('A', 10);
            put('B', 11);
            put('C', 12);
            put('D', 13);
            put('E', 14);
            put('F', 15);
            put('G', 16);
            put('H', 17);
            put('I', 18);
            put('J', 19);
            put('K', 20);
            put('L', 21);
            put('M', 22);
            put('N', 23);
            put('O', 24);
            put('P', 25);
            put('Q', 26);
            put('R', 27);
            put('S', 28);
            put('T', 29);
            put('U', 30);
            put('V', 31);
        }};

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c < 'A') {
                res += (c - '0') * Math.pow(32, n - 1 - i);
            } else {
                res += m.get(c) * Math.pow(32, n - 1 - i);
            }
        }

        return res;
    }

    private String calc10To32(int x) {
        StringBuilder res = new StringBuilder();

        Map<Integer, Character> m = new HashMap<>() {{
            put(10, 'A');
            put(11, 'B');
            put(12, 'C');
            put(13, 'D');
            put(14, 'E');
            put(15, 'F');
            put(16, 'G');
            put(17, 'H');
            put(18, 'I');
            put(19, 'J');
            put(20, 'K');
            put(21, 'L');
            put(22, 'M');
            put(23, 'N');
            put(24, 'O');
            put(25, 'P');
            put(26, 'Q');
            put(27, 'R');
            put(28, 'S');
            put(29, 'T');
            put(30, 'U');
            put(31, 'V');
        }};

        while (x > 0) {
            int t = x % 32;
            if (t < 10) {
                res.append((char) (t + '0'));
            } else {
                res.append(m.get(t));
            }

            x /= 32;
        }

        return res.reverse().toString();
    }

    @Test
    public void test6() {
        System.out.println(12 / 10);
    }

}















