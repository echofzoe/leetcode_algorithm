package JavaSE_advanced.day15_TimeAPI;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SimpleDateFormatTest
 * @Description SimpleDateFormat 类对 Date 类的格式化和解析
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/9 10:43
 */
public class SimpleDateFormatTest {
    // 1. 两个操作
    // -1.1 格式化：日期 --> 字符串
    // -1.2 解析：字符串 --> 日期，是格式化的逆过程
    // 2. SimpleDateFormat 实例化
    @Test
    public void simpleDateFormatTest() throws ParseException {
        // 1. SimpleDateFormat 实例化 - 可在构造器中指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 2. 格式化：日期 --> 字符串
        Date d1 = new Date();
        System.out.println("d1 = " + d1);
        String format = sdf.format(d1);
        System.out.println("format = " + format);
        // 3. 解析：格式化的逆过程，字符串 --> 日期
        // 注意：解析中要求字符串必须是符合 SimpleDateFormat 识别的格式（通过构造器参数体现），否则会抛出异常
        String str = "2020-6-9 11:09:10";   // 注意：日期字符串的格式有要求
        Date d2 = sdf.parse(str);
        System.out.println("d2 = " + d2);
    }

    /*
    String 转 Date 再转 sql.Date
     */
    @Test
    public void dateToSqlDate() throws ParseException {
        String birth = "2020-6-9";
        System.out.println("birth = " + birth);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birth);
        System.out.println("date = " + date);

        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println("birthDate = " + birthDate);
    }

    /*
    三天打鱼两天晒网
     */
    @Test
    public void workOrFun() throws ParseException {
        String begin = "1990-01-01";
        String target = "2020-06-12";
        // 字符串 转 日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beg = sdf.parse(begin);
        Date tar = sdf.parse(target);
        // 计算总天数 - 注意精度丢失的情况，让总天数总是 + 1
        int totalDays = (int) ((tar.getTime() - beg.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        // make decision
        switch (totalDays % 5){
            case 1: case 2: case 3:
                System.out.println("今天在奔命！");
                break;
            case 4: case 0:
                System.out.println("今天在摸鱼！");
                break;
        }
    }
}


