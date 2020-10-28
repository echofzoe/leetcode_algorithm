package JavaSE_advanced.advanced12;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @ClassName CallableTest
 * @Description 创建线程的方式三 - 实现 Callable 接口
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/6/2 15:46
 *
 */
public class CallableTest {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(numThread);
        new Thread(futureTask).start();

        try {
            // get() 返回值即为 FutureTask 构造器参数 Callable 实现类重写 call() 方法的返回值
            Integer sum = futureTask.get();
            System.out.println("\nsum: " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class NumThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
