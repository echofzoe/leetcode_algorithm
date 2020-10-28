package JavaSE_basic.basic05;

public class Person {
    public String name;
    public int age;

    public Person() {
        System.out.println("我说 Person 类的空参构造器");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("吃饭");
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object info() {
        return null;
    }

    public void show() {
        System.out.println(age);
    }
}
