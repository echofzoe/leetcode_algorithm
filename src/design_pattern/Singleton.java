package design_pattern;

import org.junit.Test;

/**
 * 单例模式
 *
 * @author echofzoe
 * @since 2021.7.9
 */
public class Singleton {

    /*
        多线程下必须加 volatile 修饰。
        instance = new Singleton() 分为三步执行：
        - 1.为 instance 分配内存空间
        - 2.初始化 instance
        - 3.将 instance 指向分配的内存地址
        由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。在多线程环境下，可能导致一个线程获取到还未初始化的实例。
        比如：线程 T1 执行完 1 和 3，此时 T2 调用 getInstance() 后发现 instance 不为空，因此返回 instance，但此时 instance 还未初始化。
     */
    private volatile static Singleton instance;

    private Singleton() {
    }

    // 双重校验机制
    public static Singleton getInstance() {
        // 校验1
        if (instance == null) {
            // 校验2
            synchronized (Singleton.class) {
                // 再次进行校验1，防止另一个线程在当前线程进行校验2的过程中正好完成了校验2
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance(), s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }

}
