package testdemo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestDemo {

    @Test
    public void test1() {
        int a = -2;
        String bin_a = Integer.toBinaryString(a);
        System.out.println(bin_a);

        a = a << 31;
        bin_a = Integer.toBinaryString(a);
        System.out.println(bin_a);
    }

    class Animal {
    }

    class Dog extends Animal {
    }

    class Cat extends Animal {
    }

    @Test
    public void test2() {
        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();

        // 定义 上限
        List<? extends Animal> test1 = cats;
        animals.add(new Cat());
    }

    /**
     * 测试序列化
     */
    @Test
    public void testSerializeAndAntiSerialize() throws IOException, ClassNotFoundException {
        String path = "D:\\CODE\\JAVA\\JavaSE_Learning\\src\\algorithm\\Phone";
        Phone phone = new Phone("apple");

        ObjectOutput output = new ObjectOutputStream(new FileOutputStream(path));
        output.writeObject(phone);
        output.flush();

        ObjectInput input = new ObjectInputStream(new FileInputStream(path));
        Phone phone1 = (Phone) input.readObject();
        System.out.println(phone1.getName());
    }

    /**
     * 测试反射
     */
    @Test
    public void testReflection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 1. 获得 Class 对象
        Class clazz = Class.forName("testdemo.Phone");

        // 2. 通过无参构造器创建对象
        Object o1 = clazz.getDeclaredConstructor().newInstance();
        if (o1 instanceof Phone) {
            System.out.println("yes");
        }
        System.out.println(o1);

        // 3. 通过有参构造器创建对象
        Constructor constructor = clazz.getConstructor(String.class);
        Phone p1 = (Phone) constructor.newInstance("zbc");
        System.out.println(p1);

        // 4. 通过反射调用普通方法
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(p1, "cc");
        System.out.println("通过反射调用 p1 对象的重命名方法，设置其名称为 " + p1.getName());

        // 5. 通过反射操作属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);    // 打开访问修饰符权限，否则不能访问 private 字段
        name.set(p1, "dd");
        System.out.println("通过反射直接操作 p1 对象的属性，设置其名称为 " + p1.getName());
    }

    public void toolMan01(Map<String, Phone> map, List<Phone> list) {
        System.out.println("toolMan01");
    }

    public Map<String, Phone> toolMan02() {
        System.out.println("toolMan02");
        return null;
    }

    /**
     * 测试对于泛型的反射
     */
    @Test
    public void testReflectionInGeneric() throws NoSuchMethodException {

        // 参数类型
        Method toolMan01 = TestDemo.class.getDeclaredMethod("toolMan01", Map.class, List.class);
        Type[] genericParameterTypes = toolMan01.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }

        System.out.println("--- --- --- --- --- ---");

        // 返回值类型
        Method toolMan02 = TestDemo.class.getDeclaredMethod("toolMan02", null);
        Type genericReturnType = toolMan02.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }
    }

    /**
     * 测试对于注解的反射
     */
    @Test
    public void testReflectionInAnnotation() throws ClassNotFoundException, NoSuchFieldException {
        Class clazz = Class.forName("testdemo.Phone");

        // 通过反射获得注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 通过反射获得注解的 value 值
        System.out.println("--- --- ---");
        TableAnnotation tableAnnotation = (TableAnnotation) clazz.getAnnotation(TableAnnotation.class);
        String value = tableAnnotation.value();
        System.out.println(value);

        // 通过反射获得类指定的注解
        System.out.println("--- --- ---");
        Field field = clazz.getDeclaredField("name");
        FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
        System.out.println(fieldAnnotation.columnName());
        System.out.println(fieldAnnotation.type());
        System.out.println(fieldAnnotation.length());
    }

    @Test
    public void testScanner() {
        Scanner scan = new Scanner(System.in);

        // 1. next 方式接收字符串
        System.out.println("next 方式接收: ");
        // hasNext 方法会扫描缓冲区中是否有字符，有则返回 true 并继续扫描，否则将方法阻塞等待下次输入
        while (!scan.hasNext("q")) {
            System.out.println("输入的数据为: " + scan.next());
        }

        // 2. nextLine 方式接收字符串
        System.out.println("nextLine 方式接收: ");
        while (scan.hasNextLine()) {
            System.out.println("输入的数据为: " + scan.nextLine());
        }

        scan.close();

        /*
        ||| next() 与 nextLine() 的区别 |||
        - next():
            1、一定要读取到有效字符后才可以结束输入。
            2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
            3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
            4、next() 不能得到带有空格的字符串。
        - nextLine():
            1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
            2、可以获得空白。
         */
    }

    @Test
    public void testStream() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char c;
        String str;

        System.out.println("输入字符，以'q'结束: ");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');

        System.out.println("\n输入字符串，以\"end\"结束: ");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("end"));
    }

    @Test
    public void testIoStream() throws IOException {
        File f = new File("testText.txt");

        FileOutputStream fop = new FileOutputStream(f);
        // 构建 OutputStreamWriter 对象,参数可以指定编码,默认为操作系统默认编码, windows 上是 gbk
        OutputStreamWriter writer = new OutputStreamWriter(fop, StandardCharsets.UTF_8);
        // 写入到缓冲区
        writer.append("中文输入");
        writer.append("\r\n");
        writer.append("English");
        writer.close();    // 关闭写入流,同时会把缓冲区内容写入文件
        fop.close();    // 关闭输出流,释放系统资源


        FileInputStream fip = new FileInputStream(f);
        InputStreamReader reader = new InputStreamReader(fip, StandardCharsets.UTF_8);
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
        }
        System.out.println(sb.toString());
        reader.close();
        fip.close();
    }
}

/**
 * 自定义注解
 */

// 对于类名的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableAnnotation {
    String value();
}

// 对于属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAnnotation {
    String columnName();

    String type();

    int length();
}
