package JavaSE_advanced.advanced13;

/**
 *
 * @ClassName ProducerConsumerTest
 * @Description 生产者消费者问题：生产者将产品交给店员，而消费者从店员处取走商品；店员一次只能持有固定数量的商品（20个）；如果生产者试图生产更多（超过20）的产品，店员会让生生产者停一下，如果店中有空位（小于20）放产品了再通知生产者继续生产；如果店中没有产品了，店员会让消费者等一下，如果店中有产品了再通知消费者取走产品。
 * @Author echofzoe
 * @Version v1.0
 * @Date 2020/5/31 17:04
 *
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        // 店员
        Clerk clerk = new Clerk();
        // 生产者
        Producer p1 = new Producer(clerk);
        p1.setName("生产者 1");

        // 消费者
        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);
        c1.setName("消费者 1");
        c2.setName("消费者 2");

        // 启动线程
        p1.start();
        c1.start();
        c2.start();
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始生产产品......");
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始消费产品......");
        while (true) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}

class Clerk {

    private int productCount = 0;

    public synchronized void produceProduct() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + "：开始生成第 " + productCount + " 个产品");

            // 唤醒消费者线程
            notify();

        } else {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + "：开始消费第 " + productCount + " 个产品");
            productCount--;

            // 唤醒生产者者线程
            notify();

        } else {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}