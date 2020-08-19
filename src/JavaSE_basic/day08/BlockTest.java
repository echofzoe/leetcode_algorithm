package JavaSE_basic.day08;

public class BlockTest {
    public static void main(String[] args) {

        String desc = Person.desc;   // 此时加载了类，会自动调用静态代码块

        Person p1 =new Person();   // 此时创建了类的实例对象，才会调用非静态代码块

    }
}

class Person {
    String name;
    int age;
    static String desc = "I am a Person.";

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 静态代码块
    static {
        System.out.println("this is static block.");
    }

    // 非静态代码块
    {
        System.out.println("this is non-static block.");
    }
}