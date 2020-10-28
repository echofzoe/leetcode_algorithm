package JavaSE_basic.basic09;

public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(Flyable.MAX_SPEED);
        System.out.println(Flyable.MIN_SPEED);

        // 1.接口的非匿名实现类的非匿名对象
        Plane p1 = new Plane();
        p1.fly();

        // 2.接口的非匿名实现类的匿名对象
        new Bullet().fly();

        // 3.接口的匿名实现类的非匿名对象
        Attackable bullet = new Attackable() {
            @Override
            public void attack() {
                System.out.println("这是匿名实现类的非匿名对象的方法");
            }

            @Override
            public void fly() {
                System.out.println("这是匿名实现类的非匿名对象的方法");
            }

            @Override
            public void stop() {
                System.out.println("这是匿名实现类的非匿名对象的方法");
            }
        };
        bullet.attack();

        // 4.接口的匿名实现类的匿名对象
        new Attackable() {
            @Override
            public void attack() {
                System.out.println("这是匿名实现类的匿名对象的方法");
            }

            @Override
            public void fly() {
                System.out.println("这是匿名实现类的匿名对象的方法");
            }

            @Override
            public void stop() {
                System.out.println("这是匿名实现类的匿名对象的方法");
            }
        }.attack();


        // JDK8 接口新特性
        NewFeatureClass nf = new NewFeatureClass();
//        nf.method_1();   // 无法调用，接口中定义的静态方法，只能通过接口来调用
        NewFeature.method_1();
        nf.method_2();   //
        nf.method_3();
    }
}

interface Flyable {
    // 全局常量
    public static final int MAX_SPEED = 7900;
    int MIN_SPEED = 1;   // 省略了 public static final

    // 抽象方法
    public abstract void fly();

    void stop();   // 省略了 public abstract
}

interface Attackable extends Flyable {
    void attack();
}

class Plane implements Flyable {

    @Override
    public void fly() {
        System.out.println("飞机飞");
    }

    @Override
    public void stop() {
        System.out.println("飞机停");
    }
}

//class Bullet implements Flyable, Attackable {
class Bullet implements Attackable {

    @Override
    public void fly() {
        System.out.println("子弹飞");
    }

    @Override
    public void stop() {
        System.out.println("子弹壳落地");
    }

    @Override
    public void attack() {
        System.out.println("子弹具有攻击性");
    }
}

// JDK8 接口新特性
interface NewFeature {
    // 静态方法
    public static void method_1() {
        System.out.println("这是新特性接口中的静态方法");
    }

    // 默认方法
    public default void method_2() {
        System.out.println("这是新特性接口中的默认方法1");
    }

    default void method_3() {
        System.out.println("这是新特性接口中的默认方法2");
    }
}

class NewFeatureClass implements NewFeature {
    ;
}