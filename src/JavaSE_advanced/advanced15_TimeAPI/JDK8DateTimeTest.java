package JavaSE_advanced.advanced15_TimeAPI;

import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @ClassName JDK8DateTimeTest
 * @Description JDK8新增时间API
 * @Author echofzoe
 * @Version V1.0
 * @Date 2020/7/18 17:42
 */
public class JDK8DateTimeTest {
    /**
     * LocalDate, LocalTime & LocalDateTime
     */
    @Test
    public void test1() {
        // 实例化 - now() 方法
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // 实例化 - of() 方法
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 7, 18, 18, 00, 00);
        System.out.println(localDateTime1);

        // 获取日期 - getXxx()
        System.out.println("\n" + localDateTime.getDayOfMonth());   // 几号
        System.out.println(localDateTime.getDayOfWeek());   // 星期几
        System.out.println(localDateTime.getMonth());   // 几月 - 英文
        System.out.println(localDateTime.getMonthValue());   // 几月 - 阿拉伯数字

        // 设置日期 - 不可变性 - withXxx()
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(8);
        System.out.println("\nlocalDateTime = " + localDateTime);   // 老数据没变
        System.out.println("localDateTime2 = " + localDateTime2);   // 新数据变了

        // 更改日期 - plusXxx 加 - minusXxx 减
        LocalDateTime localDateTime3 = localDateTime.plusDays(2);
        LocalDateTime localDateTime4 = localDateTime.minusDays(2);
        System.out.println("\nlocalDateTime = " + localDateTime);   // 老数据没变
        System.out.println("\nlocalDateTime3 = " + localDateTime3);
        System.out.println("localDateTime4 = " + localDateTime4);
    }

    /**
     * Instant - 瞬时（调用时刻）时间点
     */
    @Test
    public void test2() {
        // 获得调用时刻的瞬时时间点（基于本初子午线的标准时间，我国位于东8区，需加上8小时）
        Instant now = Instant.now();
        System.out.println(now);

        // 调整时区 - 加 x 小时
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);

        // 获取瞬时时间点对应的毫秒数（从格林威治标准时间开始的偏移量） - 相当于 Date 类的 getTime() 方法
        long epochMilli = now.toEpochMilli();
        System.out.println("epochMilli = " + epochMilli);

        // 通过毫秒数实例化瞬时时间点 - 相当于 new Date(long millis)
        Instant now1 = Instant.ofEpochMilli(1597763260335L);
        System.out.println(now1);
    }
}
