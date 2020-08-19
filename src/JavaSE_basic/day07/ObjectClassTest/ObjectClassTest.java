package JavaSE_basic.day07.ObjectClassTest;

import JavaSE_basic.day07.InstanceofTest.Man;
import JavaSE_basic.day07.InstanceofTest.Person;
import org.junit.jupiter.api.Test;

public class ObjectClassTest {

    @Test
    public void test1() {
        System.out.println("This is a test");
    }


    public static void main(String[] args) {
        Person p1 = new Person();
        p1.name = "abc";
        System.out.println(p1.getClass().getSuperclass());

        Person m1 = new Man();
        m1.name = "abc";
        System.out.println(m1.getClass());

        System.out.println();

        // equals() 方法
        System.out.println(p1.equals(m1));   // false
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println(str1.equals(str2));   // true，因为 String 对 Object 的 equals 方法进行了重写

        System.out.println();

        // toString() 方法
        System.out.println(p1.toString());
    }
}
