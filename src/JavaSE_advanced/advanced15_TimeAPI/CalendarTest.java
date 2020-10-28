package JavaSE_advanced.advanced15_TimeAPI;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName CalendarTest
 * @Description CalendarTest类的使用
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/12 1:12
 */
public class CalendarTest {
    @Test
    public void testCalendar() throws ParseException {
        // 1.实例化
        Calendar calendar = Calendar.getInstance();
        // 2.常用方法
        // get()
        int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        int day_of_year = calendar.get(6);
        System.out.println("day_of_month = " + day_of_month + " and " + "day_of_year = " + day_of_year);

        // set()
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("after set alter, day_of_month = " + day_of_month);

        // add()
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("after add alter, day_of_month = " + day_of_month);

        // getTime() - Calendar 转 Date
        Date time = calendar.getTime();
        System.out.println("time = " + time);

        // setTime() - Date 转 Calendar
        String current_str = "2020-06-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date current = sdf.parse(current_str);
        calendar.setTime(current);
        day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("day_of_month = " + day_of_month);
    }
}
