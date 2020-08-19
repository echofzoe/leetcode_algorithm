package JavaSE_basic.day06.polymorphism;

public class Person {
    String name;
    int age;

    public void eat() {
        System.out.println("人，吃饭");
    }
    
    public void walk() {
        System.out.println("人，走路 ");
    }
    
    public void overloadTest() {
        System.out.println("（重载测试）父类中的方法");
    }
}
