package JavaSE_advanced.advanced12;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName SynchronizedTest_2
 * @Description 线程安全 - 同步方法方式
 * @date 2020/5/30 17:12
 */
public class SynchronizedTest_2 {
    public static void main(String[] args) {
        // 实现 Runnable 接口的方式
        Thread_03 a1 = new Thread_03();
        Thread aa1 = new Thread(a1);
        Thread aa2 = new Thread(a1);
        aa1.start();
        aa2.start();

        // 继承 Thread 类的方式
        Thread_04 b1 = new Thread_04();
        Thread_04 b2 = new Thread_04();
        b1.start();
        b2.start();
    }
}

class Thread_03 implements Runnable {
    private int ticket = 20;
    private static boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            show();
        }
    }

    // 默认同步监视器：this
    private synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程 " + Thread.currentThread().getName() + " 抢到的票为：" + ticket);
            ticket--;
        } else {
            flag = false;
        }
    }
}

class Thread_04 extends Thread {
    private static int ticket = 20;
    private static boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            show();
        }
    }

    // 方法加上 static 限制，使得此同步方法的默认同步监视器为 Thread_04.class
    private static synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程 " + Thread.currentThread().getName() + " 抢到的票为：" + ticket);
            ticket--;
        } else {
            flag = false;
        }
    }
}
