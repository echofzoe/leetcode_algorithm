package JavaSE_advanced.advanced18_Lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();

        // 有类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 无类型声明（编译器自动识别）
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambdaTest.operate(10, 5, addition));
        // System.out.println("10 + 5 = " + lambdaTest.operate(10, 5, (x, y) -> x + y));
        System.out.println("10 - 5 = " + lambdaTest.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + lambdaTest.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambdaTest.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetingService2 = (message) -> System.out.println("Hello " + message);

        greetingService1.sayMessage("I am best");
        greetingService2.sayMessage("I am top");

        // 遍历
        List<String> list = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
            }
        };
        list.forEach(c -> System.out.println(c));
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}