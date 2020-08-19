package JavaSE_advanced.day16_Reflect;

import org.junit.jupiter.api.Test;

public class GetClassInstance {
    @Test
    public void test1() throws ClassNotFoundException {
        // 方式1. 调用运行时类的 class 属性
        Class cla1 = Person.class;
        System.out.println(cla1);

        // 方式2. 通过运行时类的对象
        Person p1 = new Person("Sheep", 24);
        Class cla2 = p1.getClass();
        System.out.println(cla2);

        // 方式3. 调用 Class 的静态方法 - forName(String classPath)
        Class cla3 = Class.forName("JavaSE_advanced.day16_Reflect.Person");
        System.out.println(cla3);

        System.out.println(cla1 == cla2);

        // 方式4. 使用类的加载器 - ClassLoader
        ClassLoader classLoader = GetClassInstance.class.getClassLoader();
        Class cla4 = classLoader.loadClass("JavaSE_advanced.day16_Reflect.Person");
        System.out.println(cla4);
    }
}
