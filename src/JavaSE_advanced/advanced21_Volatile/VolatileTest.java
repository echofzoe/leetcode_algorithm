package JavaSE_advanced.advanced21_Volatile;

public class VolatileTest {

    private static  int sharedVariable = 0;
    private static final int MAX = 10;

    public static void main(String[] args) {

        new Thread(() -> {
            int oldValue = sharedVariable;

            while (sharedVariable < MAX) {

                if (sharedVariable != oldValue) {
                    System.out.println(Thread.currentThread().getName() + " watched the change : " + oldValue + "->" + sharedVariable);
                    oldValue = sharedVariable;
                } else {
                    try {
                        System.out.println("sharedVariable:" + sharedVariable + " oldValue:" + oldValue);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(Thread.currentThread().getName() + " stop run!");
        }, "t1").start();

        new Thread(() -> {
            int oldValue = sharedVariable;

            while (sharedVariable < MAX) {
                System.out.println(Thread.currentThread().getName() + " do the change : " + sharedVariable + "->" + (++oldValue));
                sharedVariable = oldValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " stop run!");
        }, "t2").start();

    }

}
