package JavaSE_advanced.advanced12;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 *
 * @ClassName ExecutorTest
 * @Description 创建线程的方式四 - 线程池
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/3 10:16
 *
 */
public class ExecutorTest {
    public static void main(String[] args) {
        // 1. 创建一个指定线程数的可重用的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 1.1 设置线程池的属性
        ThreadPoolExecutor serv = (ThreadPoolExecutor) service;
        serv.setCorePoolSize(1);
        serv.setKeepAliveTime(10, MICROSECONDS);

        // 2. 执行指定的线程的操作，需要提供实现 Runnable 接口或 Callable 接口实现类的对象
        service.execute(new EvenNumberThread());   // 适合于执行 Runnable
        service.execute(new Thread() {   // 匿名对象
            @Override
            public void run() {
                System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行");
            }
        });

        Future oddSum = service.submit(new OddNumberThread());   // 适合于执行 Callable
        try {
            System.out.println("输出的奇数总和为：" + oddSum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        service.shutdown();
    }
}

class EvenNumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " 输出偶数：" + i);
            }
        }
    }
}

class OddNumberThread implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " 输出奇数：" + i);
                sum += i;
            }
        }
        return sum;
    }
}
