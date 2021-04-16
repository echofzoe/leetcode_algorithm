package interview.CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo02 {

    // 总访问量
    static int count = 0;

    // 模拟访问的方法 - 加锁
    public synchronized static void request() throws InterruptedException {
        // 模拟单次访问耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 开始时间
        long start = System.currentTimeMillis();
        int threadSize = 100;

        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模拟用户行为，每个用户 request 10次
                    try {
                        for (int j = 0; j < 10; j++) {
                            request();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });

            thread.start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + ", 耗时: " + (end - start) + ", count = " + count);
    }
}
