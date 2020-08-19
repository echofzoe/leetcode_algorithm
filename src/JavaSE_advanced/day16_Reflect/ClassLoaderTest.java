package JavaSE_advanced.day16_Reflect;

import org.junit.jupiter.api.Test;

public class ClassLoaderTest {
    @Test
    public void test1(){
        ClassLoader cd1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(cd1);   // 输出 AppClassLoader - 此为系统类加载器

        ClassLoader cd2 = cd1.getParent();
        System.out.println(cd2);   // 输出 PlatformClassLoader - 此为扩展类加载器

        ClassLoader cd3 = cd1.getParent();
        System.out.println(cd3);   // 输出 null - 其实扩展类加载器再往上是引导类加载器，但是引导类加载器无法直接访问
    }
}
