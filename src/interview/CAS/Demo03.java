package interview.CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo03 {

    // 总访问量
    volatile static int count = 0;

    // 模拟访问的方法 - 加锁
    public static void request() throws InterruptedException {
        // 模拟单次访问耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
//        count++;
        int expectCount;  // 表示期望值
        while (!compareAndSwap((expectCount = getCount()), expectCount + 1)) ;
    }

    /**
     * CAS
     * @param expectVal 期望值
     * @param newVal    新值
     * @return 成功返回 true 失败返回 false
     */
    public static synchronized boolean compareAndSwap(int expectVal, int newVal) {
        // 判断 count 当前值是否和期望值一致，如果一致，将新值赋给 count
        if (getCount() == expectVal) {
            count = newVal;
            return true;
        }
        return false;
    }

    public static int getCount() {
        return count;
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
