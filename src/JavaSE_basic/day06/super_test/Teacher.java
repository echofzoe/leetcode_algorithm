package JavaSE_basic.day06.super_test;

import JavaSE_basic.day05.Person;

public class Teacher extends Person {

    String major;
    // 测试 super 关键字
    int age = 22;

    public Teacher() {
        System.out.println("我是 Teacher 类的空参构造器");
    }

    public Teacher(String major) {
        this.major = major;
        System.out.println("我是 Teacher 类的有参构造器");
    }

    public void show() {
        System.out.println("子类 age = " + age);   // 默认 this
        System.out.println("父类 age = " + super.age);
    }
}
