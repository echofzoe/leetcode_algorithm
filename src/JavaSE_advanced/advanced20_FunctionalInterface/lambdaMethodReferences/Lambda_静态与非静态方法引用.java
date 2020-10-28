package JavaSE_advanced.advanced20_FunctionalInterface.lambdaMethodReferences;

/**
 * 静态与非静态方法引用
 */
public class Lambda_静态与非静态方法引用 {

    @FunctionalInterface
    private interface Calculate {
        int calc(int a, int b);
    }

    public static void main(String[] args) {
        Calculate c1 = (x, y) -> calculate(x, y);
        // 上式等价于下式 - 函数引用
        Calculate c2 = Lambda_静态与非静态方法引用::calculate;    // 静态方法引用 - 类::静态方法

        Calculate c3 = new Lambda_静态与非静态方法引用()::calculate2;    // 非静态方法引用 - 对象::非静态方法

        System.out.println(c1.calc(10, 20));
        System.out.println(c2.calc(20, 10));
        System.out.println(c3.calc(10, 20));
    }

    private static int calculate(int a, int b) {
        if (a > b) return a - b;
        else if (a < b) return b - a;
        else return a + b;
    }

    private int calculate2(int a, int b) {
        if (a != b) return a - b;
        return a + b;
    }
}
