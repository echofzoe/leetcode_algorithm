package JavaSE_advanced.day14_String;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 *
 * @ClassName StringTest
 * @Description String 类的使用
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/6 14:58
 *
 */
public class StringTest {
    @Test
    public void test_1() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println("s1：" + s1 + "，s2：" + s2);
        System.out.println("s1 和 s2 地址是否相同：" + (s1 == s2));

        s1 = "hello";
        System.out.println("\ns1：" + s1 + "，s2：" + s2);
        System.out.println("s1 和 s2 地址是否相同：" + (s1 == s2));

        String s3 = "abc";
        System.out.println("\ns2：" + s2 + "，s3：" + s3);
        System.out.println("s2 和 s3 地址是否相同：" + (s2 == s3));

        s3 += "def";
        System.out.println("\ns2：" + s2 + "，s3：" + s3);
        System.out.println("s2 和 s3 地址是否相同：" + (s2 == s3));

        String s4 = s3.replace('a', 'z');
        System.out.println("\ns3：" + s3 + "，s4：" + s4);
        System.out.println("s3 和 s4 地址是否相同：" + (s3 == s4));
    }

    @Test
    public void stringToCharArray() {
        String str1 = new String("abc");
        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
        }
    }

    @Test
    public void charArrayToString() {
        char[] c1 = new char[]{'a', 'b', 'c'};
        String str1 = new String(c1);
        System.out.println(str1);
    }

    @Test
    public void stringToXxx() {
        String str1 = "11";
        String str2 = "12.0001";
        int i1 = Integer.parseInt(str1);
        Double d1 = Double.parseDouble(str2);
        System.out.println(i1 + " " + d1);
    }

    @Test
    public void xxxToString() {
        Integer i1 = new Integer(21);
        double d1 = 22.0001;
        String str1 = String.valueOf(i1);
        String str2 = String.valueOf(d1);
        System.out.println(str1 + " " + str2);
    }

    @Test
    public void reversePartCharArray() {
        // 反转 "abcdef" 的部分内容使之变成 "aedcbf"
        String str = "abcdef";
        char[] array = str.toCharArray();
        int len = array.length;
        for (int i = 1; i < len / 2; i++) {
            char tmp = array[i];
            array[i] = array[len - 1 - i];
            array[len - 1 - i] = tmp;
        }
        str = String.valueOf(array);
        System.out.println(str);
    }

    @Test
    public void stringToByteArray() {
        String str = "abc123中国";
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(bytes));
    }

    @Test
    public void byteArrayToString() {
        byte[] bytes = new byte[]{97, 98, 99, 49, 50, 51, -21, -22};
        String str = null;
        try {
            str = new String(bytes, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
