package JavaSE_basic.basic06.override_test;

import JavaSE_basic.basic05.Person;

public class Teacher extends Person{

    String major;

    public Teacher() {

    }

    public Teacher(String major) {
        this.major = major;
    }

    public void teach() {
        System.out.println("我教授的专业是" + major);
    }

    public void eat() {
        System.out.println("吃食堂");
    }

    // 重写父类方法，返回值类型可以是父类被重写方法返回值类型的子类
    public String info() {
        return null;
    }
}
