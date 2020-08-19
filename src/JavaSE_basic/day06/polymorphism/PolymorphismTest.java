package JavaSE_basic.day06.polymorphism;

import java.sql.Connection;

public class PolymorphismTest {
    public static void main(String[] args) {

//        Person p1 = new Man();
//        Person p2 = new Women();
//        p1.eat();

        PolymorphismTest pt = new PolymorphismTest();
        pt.func(new Person());

        // 有了多态性之后，就不用再重新写两个专门的方法去接收 Man 和 Women 对象了
        pt.func(new Man());
        pt.func(new Women());

        // 继承关系下类的重载测试
        Man m1 = new Man();
        m1.overloadTest();   // 继承的父类方法
        m1.overloadTest(0);   // 重载后的子类方法
    }

    public void func(Person person) {
        person.eat();
        person.walk();
    }

    // 举例
    public void tackleData(Connection conn) {   // conn = new MySQLConnection();

    }
}
