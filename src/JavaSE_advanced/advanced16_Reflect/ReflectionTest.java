package JavaSE_advanced.advanced16_Reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    @Test
    public void test1() throws Exception {
        Class cla = Person.class;   // 类名
        // 1. 通过反射，调用构造器，创建 Person 类的对象
        // 1.1 调用共有构造器
        Constructor cons = cla.getDeclaredConstructor(String.class, int.class);   // 属性
        Object obj = cons.newInstance("Tom", 12);   // 实例化
        Person p1 = (Person) obj;   // 类型转化
        System.out.print("p1 的信息：");
        p1.show();

        // 1.2 调用私有构造器
        Constructor cons1 = cla.getDeclaredConstructor(String.class);   // 属性
        cons1.setAccessible(true);   // 私有构造器，要先打开访问权限
        Person p2 = (Person) cons1.newInstance("Cat");   // 实例化
        System.out.print("p2 的信息：");
        p2.show();

        // 2. 通过反射，调用对象指定的属性、方法（私有属性或方法，调用前要设置访问权限）
        // 2.1 调属性
        // 2.1.1 设置私有属性
        Field name = cla.getDeclaredField("name");
        name.setAccessible(true);   // 设置私有属性，要先打开访问权限
        name.set(p1, "Cat");   // 设置私有属性
        Field age = cla.getDeclaredField("age");
        age.setAccessible(true);   // 设置私有属性，要先打开访问权限
        age.set(p1, 18);
        // 2.1.2 设置公有属性
        Field sex = cla.getDeclaredField("isMale");
        sex.setBoolean(p1, false);   // 公有属性的访问权限默认打开，可直接设置
        System.out.print("更改后的 p1 的信息：");
        p1.show();

        // 2.2 调方法
        // 2.2.1 调公有、无参方法
        Method showCity = cla.getDeclaredMethod("showCity", String.class);
        System.out.print("通过反射调用的公有无参方法：");
        showCity.invoke(p1, "Tokyo");   // 相当于 p1.showCity("Tokyo")
        // 2.2.2 调私有、有参方法
        Method showNation = cla.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);   // 先打开访问权限
        System.out.print("通过反射调用的私有有参方法：");
        showNation.invoke(p1, "Japan");
    }
}
