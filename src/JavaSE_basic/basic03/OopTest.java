package JavaSE_basic.basic03;

public class OopTest {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.age);
        p.test("sb");
    }
}

class Person {
    String name;
    int age;
    boolean isMale;

    public void test(String arg) {
        System.out.println("your " + arg);
    }
}
