package JavaSE_advanced.day15_TimeAPI;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 *
 * @ClassName DateTimeTest
 * @Description JDK8 之前日期和时间的 API 测试
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/7 16:50
 *
 */
public class DateTimeTest {
    // System 类中的 currentTimeMillis() 方法 - 返回现在时间与 1970.1.1.0 之间的以毫秒为单位的时间差
    @Test
    public void test_1() {
        long time = System.currentTimeMillis();
        System.out.println(time);
    }

    // 两个 Date 类 - 两个构造器、两个方法的使用
    @Test
    public void test_2() {
        // java.util.Date 类
        Date d1 = new Date();
        Date d2 = new Date(333333333L);
        // toString() - 显示当前的年月日时分秒
        System.out.println(d2.toString());
        // getTime() - 获取当前 Date 对象对应的时间戳
        System.out.println(d1.getTime());

        // java.sql.Date 类
        java.sql.Date d3 = new java.sql.Date(333333333L);
        System.out.println("\n" + d3);

        // java.util.Date 转 java.sql.Date
        Date d4 = new Date();
        java.sql.Date d5 = new java.sql.Date(d4.getTime());
        System.out.println("\n" + d5);
    }
}
