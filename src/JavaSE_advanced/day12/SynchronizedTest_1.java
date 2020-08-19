package JavaSE_advanced.day12;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName SynchronizedTest_1
 * @Description 线程安全 - 同步代码块方式
 * @date 2020/5/30 17:12
 */
public class SynchronizedTest_1 {
    public static void main(String[] args) {
        // 实现 Runnable 接口的方式
        Thread_01 a1 = new Thread_01();
        Thread aa1 = new Thread(a1);
        Thread aa2 = new Thread(a1);
        aa1.start();
        aa2.start();

        // 继承 Thread 类的方式
        Thread_02 b1 = new Thread_02();
        Thread_02 b2 = new Thread_02();
        b1.start();
        b2.start();
    }
}

class Thread_01 implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程 " + Thread.currentThread().getName() + " 抢到的票为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

class Thread_02 extends Thread {
    private static int ticket = 20;

    @Override
    public void run() {
        while (true) {
            synchronized (Thread_02.class) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程 " + Thread.currentThread().getName() + " 抢到的票为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
