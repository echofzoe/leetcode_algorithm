package JavaSE_advanced.day12;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @ClassName LockTest
 * @Description 线程安全 - Lock 锁 - JDK 5.0 新增特性
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/5/30 20:40
 *
 */
public class LockTest {
    public static void main(String[] args) {
        Windows w1 = new Windows();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Windows implements Runnable {
    private int ticket = 100;

    // 1. 实例化 ReentrantLock
    private ReentrantLock lock =new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                // 2. 加锁 - 调用 lock() 方法
                lock.lock();

                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：售票，票号为 " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
            finally {
                // 3. 解锁 - 调用 unlock() 方法
                lock.unlock();
            }
        }
    }
}
