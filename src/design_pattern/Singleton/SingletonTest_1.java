package design_pattern.Singleton;


public class SingletonTest_1 {

    // 饿汉式单例模式
    public static void main(String[] args) {
        Bank b1 = Bank.getInstance();
        Bank b2 = Bank.getInstance();
        System.out.println(b1.equals(b2));
    }
}

class Bank {

    // 1.私有化类的构造器
    private Bank() {

    }

    // 2.内部创建类的对象
    private static Bank instance = new Bank();

    // 3.提供公共的方法用于返回类的对象
    public static Bank getInstance() {
        return instance;
    }
}