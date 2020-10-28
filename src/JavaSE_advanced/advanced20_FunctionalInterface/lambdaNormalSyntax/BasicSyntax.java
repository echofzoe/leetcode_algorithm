package JavaSE_advanced.advanced20_FunctionalInterface.lambdaNormalSyntax;

/**
 * lambda表达式的基础语法
 */
public class BasicSyntax {
    public static void main(String[] args) {
        // 1. 实现无返回值、无参的函数式接口
        NoneReturnNoneParameter lambda1 = () -> System.out.println("这是一个无返回值、无参的方法");
        lambda1.test();

        // 2. 实现无返回值、一个参数的函数式接口
        NoneReturnSingleParameter lambda2 = (int a) -> System.out.println("这是一个无返回值、一个参数的方法，参数 a:" + a);
        lambda2.test(10);

        // 3. 实现无返回值、多个参数的函数式接口
        NoneReturnMultipleParameters lambda3 = (int a, int b) -> System.out.println("这是无返回值、多个参数的方法，参数 a: " + a + ", b: " + b);
        lambda3.test(10, 20);

        System.out.println("------------分割线------------");

        // 4. 实现有返回值、无参的函数式接口
        SingleReturnNoneParameter lambda4 = () -> {
            System.out.println("这是有返回值、无参的方法，返回值是: " + 10);
            return 10;
        };
        System.out.println(lambda4.test());

        // 5. 实现有返回值、一个参数的函数式接口
        SingleReturnSingleParameter lambda5 = (int a) -> {
            System.out.println("这是有返回值、一个参数的方法，返回值是: " + a);
            return a;
        };
        System.out.println(lambda5.test(100));

        // 6. 实现有返回值、多个参数的函数式接口
        SingleReturnMultipleParameters lambda6 = (int a, int b) -> {
            System.out.println("这是有返回值、多个参数的方法，返回值是: " + (a + b));
            return a + b;
        };
        System.out.println(lambda6.test('a', 200));
    }
}
