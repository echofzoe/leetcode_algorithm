package JavaSE_basic.basic11;

public class ErrorAndExceptionTest {
    public static void main(String[] args) {
        // 1.Error
        // - 栈溢出 - java.lang.StackOverflowError
        // main(args);
        // - 堆溢出 - java.lang.OutOfMemoryError
        // Integer[] arr = new Integer[1024 * 1024 * 1024];

        // 2.Exception
        int[][] num1 = new int[4][];
        // - 运行时异常 - java.lang.NullPointerException
        System.out.println(num1[0][0]);
    }
}
