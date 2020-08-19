package JavaSE_basic.day05;

public class Student extends Person{
    //    private String name;
//    private int age;
    private String major;

    public Student() {

    }

    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    public void study() {
        System.out.println("学习");
    }
}

