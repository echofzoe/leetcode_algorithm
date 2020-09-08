package algorithm.testdemo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class testdemo {

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

}

class Phone implements Serializable {
    private String name;

    public Phone() {
        System.out.println("Phone()");
    }

    public Phone(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
