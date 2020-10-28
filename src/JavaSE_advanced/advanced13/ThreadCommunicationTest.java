package JavaSE_advanced.advanced13;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @ClassName ThreadCommunicationTest
 * @Description 线程通信测试 - 两线程交替打印 1-10
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/5/31 15:52
 *
 */
public class ThreadCommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);
        Thread t3 = new Thread(number);

        t1.setName("线程 1");
        t2.setName("线程 2");
        t3.setName("线程 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Number implements Runnable {
    private int number = 1;
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notifyAll();

                if (number <= 10) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        // 阻塞一下
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}
