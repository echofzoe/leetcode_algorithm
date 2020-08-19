package JavaSE_basic.day06.super_test;

import JavaSE_basic.day05.Person;

public class SuperTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.show();
        System.out.println();

        Teacher t1 = new Teacher();
        t1.show();
        System.out.println();

        Teacher t2 = new Teacher("英语");
        t2.show();
    }
}