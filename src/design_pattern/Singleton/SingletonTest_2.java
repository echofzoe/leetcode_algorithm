package design_pattern.Singleton;

public class SingletonTest_2 {

    // 懒汉式单例模式
    public static void main(String[] args) {
        Order o1 = Order.getInstance();
        Order o2 = Order.getInstance();
        System.out.println(o1 == o2);
    }
}

class Order {

    // 1.私有化类的构造器
    private Order() {
    }

    // 2.内部声明类的对象，没有初始化
    private static Order instance = null;

    // 3.提供公共的方法用于初始化（如果未初始化）并返回类的对象
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

}
