package JavaSE_advanced.advanced20_FunctionalInterface.lambdaMethodReferences;

public class Lambda_注意事项 {

    private static int y = 100;

    public static void main(String[] args) {
        // 1. 定义一个局部变量
        int x = 10;
        // 2. 使用lambda表达式实现接口
        LambdaTest lambda = () -> {
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        };

        // 3. 修改变量的值
        // x = 20;
        y = 200;
    }
}

@FunctionalInterface
interface LambdaTest {
    void test();
}
