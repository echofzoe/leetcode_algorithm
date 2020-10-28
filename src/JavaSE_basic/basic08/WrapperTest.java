package JavaSE_basic.basic08;

import org.junit.jupiter.api.Test;

public class WrapperTest {

    @Test
    public void testWrapper_Numbers() {
        // 构造器
        int num1 = 10;
        Integer num2 = new Integer(num1);
        System.out.println("num2 = " + num2.toString());

        Integer num3 = new Integer("20");
        System.out.println("num3 = " + num3.toString());

        // intValue() 方法
        int num4 = num3.intValue();
        System.out.println("\n" + num4);
        float f1 = new Float(12.3f).floatValue();
        System.out.println(f1);
    }

    @Test
    public void testWrapper_Boolean() {
        UselessClass uc = new UselessClass();
        System.out.println(uc.isMale);   // 输出false，因为类初始化时布尔的基本数据类型默认初始化为 false
        System.out.println(uc.isFemale);   // 输出null，因为此时的布尔为包装类，只声明未赋值，引用地址为 null

        // 构造器
        Boolean b1 = new Boolean(true);   // 字面意思
        Boolean b2 = new Boolean("false");   // 字面意思
        Boolean b3 = new Boolean("abc");   // String 实体内容非 "true"，皆输出 false
        System.out.println("\nb1 = " + b1 + ", b2 = " + b2 + ", b3 = " + b3);
    }

    @Test
    public void testAutopacking() {
        // 自动装箱：基本数据类型 ---> 包装类的对象
        int num1 = 8;
        Integer int1 = num1;
        System.out.println("自动装箱测试" + int1);

        // 自动拆箱：包装类的对象 ---> 基本数据类型
        num1 = int1;
        System.out.println("自动拆箱测试" + num1);
    }

    @Test
    public void testToString() {
        // 基本数据类型、包装类 ---> String 类型
        int num1 = 18;
        // 方式1
        String str1 = num1 + "";
        // 方式2
        String str2 = String.valueOf(num1);
        String str3 = String.valueOf(new Float(12.3f));

        System.out.println("str1 = " + str1 + ", str2 = " + str2 + ", str3 = " + str3);
    }
}

class UselessClass {
    boolean isMale;
    Boolean isFemale;
}