package JavaSE_basic.basic06.polymorphism;

public class Man extends Person {

    boolean isSmoking;

    public void earnMoney() {
        System.out.println("男人挣钱");
    }

    public void eat() {
        System.out.println("男人吃肉");
    }

    public void walk() {
        System.out.println("男人开车");
    }

    public void overloadTest(int num) {
        System.out.println("（重载测试）子类中的重载方法");
    }
}
