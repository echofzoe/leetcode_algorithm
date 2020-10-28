package JavaSE_advanced.advanced17_Generics;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    // 泛型方法示例
    // - 1. 所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的<E>）
    // - 2. 每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符
    // - 3. 类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符
    // - 4. 泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型（像int,double,char的等）
    public static <E> void printArray(E[] inputArray) {
        // 输出数组元素
        for (E element :
                inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // 有界的类型参数的泛型
    // - 1. 一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的
    // - 2. 要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }


    // 泛型类
    public static class Box<T> {
        private T t;

        private void add(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }

    // 类型通配符
    // - 1. 类型通配符一般是使用?代替具体的类型参数。例如 List<?> 在逻辑上是List<String>,List<Integer> 等所有List<具体类型实参>的父类
    public static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }

    // - 2. 类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型
    public static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }

    @Test
    public void test1() {
        // 普通泛型方法
        Integer[] integers = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.1, 3.1, 4.1, 5.1};
        Character[] characters = {'H', 'E', 'L', 'L', 'O'};

        System.out.println("整型数组元素为:");
        printArray(integers); // 传递一个整型数组

        System.out.println("\n双精度型数组元素为:");
        printArray(doubles); // 传递一个双精度型数组

        System.out.println("\n字符型数组元素为:");
        printArray(characters); // 传递一个字符型数组
    }

    @Test
    public void test2() {
        // 带有有界类型参数的泛型方法
        System.out.printf("%d, %d 和 %d 中最大的数为 %d\n\n",
                3, 4, 5, maximum(3, 4, 5));

        System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
                6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));

        System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear",
                "apple", "orange", maximum("pear", "apple", "orange"));
    }

    @Test
    public void test3() {
        // 泛型类
        // - 1. 声明时不指定泛型具体值
        Box fusionBox = new Box();
        fusionBox.add(Integer.valueOf(10));
        System.out.printf("整型值为 :%d\n\n", fusionBox.get());

        fusionBox.add("cc is best");
        System.out.printf("字符串为 :%s\n\n", fusionBox.get());

        // - 2. 声明时指定泛型具体值
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(Integer.valueOf(7));
        stringBox.add("dd is best");

        System.out.printf("integerBox 所存值为:%d\n\n", integerBox.get());
        System.out.printf("stringBox 所存值为:%s\n", stringBox.get());
    }

    @Test
    public void test4() {
        // 类型通配符 - 无界
        List<String> name = new ArrayList<String>();
        List<Integer> age = new ArrayList<Integer>();
        List<Number> number = new ArrayList<Number>();

        name.add("icon");
        age.add(18);
        number.add(88);

        getData(name);
        getData(age);
        getData(number);

        // 类型通配符 - 有界
        // getUperNumber 上界为 Number，无法接受 String 类型
        getUperNumber(age);
        getUperNumber(number);
    }

    @Test
    public void genericsAndReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        // list.add("a"); 在编辑和编译时，这样直接添加与泛型类型不符的数据会报错

        // 通过反射添加，则可以
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list, "a");
        System.out.println(list);
    }
}
