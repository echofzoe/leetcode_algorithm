package testdemo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
