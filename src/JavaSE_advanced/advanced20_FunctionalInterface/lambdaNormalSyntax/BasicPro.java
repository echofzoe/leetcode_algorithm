package JavaSE_advanced.advanced20_FunctionalInterface.lambdaNormalSyntax;

/**
 * lambda表达式的进阶语法
 */
public class BasicPro {
    public static void main(String[] args) {
        // 1. 参数部分的简化
        SingleReturnSingleParameter lambda1 = a -> {
            return a * a;
        };

        NoneReturnNoneParameter lambda2 = () -> {
        };

        NoneReturnMultipleParameters lambda3 = (a, b) -> {
        };

        // 2. 返回值部分的简化
        // 2.1 方法体大括号部分的简化 - 方法体内只有单句代码且非return语句时，大括号可以省略
        NoneReturnMultipleParameters lambda4 = (a, b) -> System.out.println("a = " + a + ", b = " + b);
        // 2.2 方法体大括号部分的简化 - 方法体内只有单句代码且是return语句时，大括号可以省略，且return也可以省略
        SingleReturnSingleParameter lambda5 = a -> a * a;

    }
}