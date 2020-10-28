package JavaSE_basic.basic06.polymorphism;

public class Women extends Person {

    boolean isBeauty;

    public void goShopping() {
        System.out.println("女人购物");
    }

    public void eat() {
        System.out.println("女人吃水果");
    }

    public void walk() {
        System.out.println("女人坐车");
    }
}
