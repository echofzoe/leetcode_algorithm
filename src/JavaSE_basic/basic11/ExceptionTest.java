package JavaSE_basic.basic11;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {
    @Test
    public void test_1() {
        String str = "abc";

        // try - catch - finally 异常处理方式
        // 1.按照 catch 代码块的顺序，一旦某个 catch 块执行了，后面的 catch 块便不再执行
        // 2.finally 代码块是可选的，可以省略不写
        // 3.常用处理方式
        //   - e.printStackTrace(); - 打印异常语句
        //   - e.getMessage() - 返回 String 类型的异常信息
        try {
            int num = Integer.parseInt(str);
            System.out.println("执行了 try 语句块\n");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("执行了 catch 语句块 - 出现了数值转换异常\n");
        } catch (NullPointerException e) {
            System.out.println("执行了 catch 语句块 - 出现了空指针异常\n");
        } catch (Exception e) {
            System.out.println("执行了 catch 语句块 - 出现了异常\n");
        } finally {
            System.out.println("finally 代码块是可选的，可以省略不写\n");
        }
    }

    @Test
    public void test_2() {
        // throws + 异常类型
        // 1.将异常向上抛给调用者，希望调用者处理，自己并没有真正处理掉异常
        // 2.若一直向上抛但都不处理，则最后会抛给 JVM 虚拟机处理
        try {
            method_1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void method_1() throws IOException {
        method_2();
    }

    public void method_2() throws FileNotFoundException, IOException {
        File file = new File("hello.txt");
        FileInputStream fls = new FileInputStream(file);

        int data = fls.read();
        while (data != -1) {
            System.out.print((char) data);
            data = fls.read();
        }

        fls.close();
    }

    @Test
    public void test_3() {
        int num = -1;
        if (num < 0) {
            // System.out.println("异常：输入整数越界");
            throw new RuntimeException("异常：输入整数越界");
        } else {
            System.out.println(num);
        }
    }
}
