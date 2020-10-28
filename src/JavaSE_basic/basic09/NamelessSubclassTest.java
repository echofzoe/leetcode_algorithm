package JavaSE_basic.basic09;

public class NamelessSubclassTest {
    public static void main(String[] args) {

        // 非匿名类、非匿名对象
        Student t1 = new Student();
        method(t1);

        // 非匿名类、匿名对象
        method(new Teacher());

        // 匿名类、匿名对象
        method(new Person() {
            @Override
            public void eat() {
                System.out.println("匿名对象吃饭");
            }

            @Override
            public void walk() {
                System.out.println("匿名对象走路\n");
            }
        });
    }

    public static void method(Person p){
        p.eat();
        p.walk();
    }
}

abstract class Person {
    abstract public void eat();

    abstract public void walk();
}

class Student extends Person {
    public void eat() {
        System.out.println("学生吃饭");
    }

    public void walk() {
        System.out.println("学生走路\n");
    }
}

class Teacher extends Person {
    public void eat() {
        System.out.println("老师吃饭");
    }

    public void walk() {
        System.out.println("老师走路\n");
    }
}
