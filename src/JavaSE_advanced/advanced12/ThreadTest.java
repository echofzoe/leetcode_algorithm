package JavaSE_advanced.advanced12;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName ThreadTest
 * @Description 多线程测试
 * @date 2020/5/29 14:59
 */
public class ThreadTest {
    public static void main(String[] args) {
        // 1.3 创建子类对象
        MyThread mt = new MyThread();

        // 1.4 通过子类对象调用 start() 方法
        mt.start();

        System.out.println("我是 " + Thread.currentThread().getName() + " 线程");

        // practice
        OddNums odds = new OddNums();
        odds.start();
        EvenNums evens = new EvenNums();
        evens.start();

        // 匿名子类
        new Thread(){
            @Override
            public void run() {
                System.out.println("我是匿名子类的 " + Thread.currentThread().getName() + " 线程");
            }
        }.start();

        // 2.3 创建实现类的对象
        MyMethod m1 = new MyMethod();
        // 2.4 将此对象作为参数传递到 Thread 类的构造器中，创建 Thread 类的对象
        // 2.5 通过 Thread 类的对象调用 start()
        new Thread(m1).start();
    }
}

// 1.1 创建一个继承于 Thread 类的子类
class MyThread extends Thread {
    // 1.2 重写 run() 方法
    @Override
    public void run() {
        System.out.println("我是 " + Thread.currentThread().getName() + " 线程");
    }
}

class EvenNums extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " is a even number");
            }
        }
    }
}

class OddNums extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                System.out.println(i + " is a odd number");
            }
        }
    }
}

// 2.1 创建一个实现了 Runnable 接口的类
class MyMethod implements Runnable {
    // 2.2 实现类去实现 Runnable 中的抽象方法 run()
    @Override
    public void run() {
        System.out.println("我是 Runnable 接口实现的 " + Thread.currentThread().getName() + " 线程");
    }
}
