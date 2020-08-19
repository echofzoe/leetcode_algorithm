package JavaSE_advanced.day14_String;

import org.junit.jupiter.api.Test;

/**
 *
 * @ClassName StringBufferBuilderTest
 * @Description 关于 StringBuffer类 和 StringBuilder类 的使用
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/7 11:07
 *
 */
public class StringBufferBuilderTest {
    @Test
    public void StringBufferTest() {
        StringBuffer stringBuffer = new StringBuffer("abc");
        stringBuffer.setCharAt(0, 'm');
        System.out.println(stringBuffer);
    }

    @Test
    public void StringBuilderTest() {
        StringBuilder stringBuilder = new StringBuilder("abcdef");
        stringBuilder.replace(1, 4, "mm");
        System.out.println(stringBuilder);
        stringBuilder.delete(1, 3);
        System.out.println(stringBuilder);
        stringBuilder.insert(1, true);
        System.out.println(stringBuilder);
    }

    @Test
    public void splitTest() {
        String str = "alabclabdlabelabfla";
        String[] split = str.split("l*l");
        for (int i = 0; i < split.length; i++) {
            System.out.print(split[i] + ", ");
        }
    }

    /**
     * 对比 String、StringBuffer、StringBuilder 三者的效率
     */
    @Test
    public void efficient() {
        // 初始设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");

        // 开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer 的执行时间为：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder 的执行时间为：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            text += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String 的执行时间为：" + (endTime - startTime));
    }
}
