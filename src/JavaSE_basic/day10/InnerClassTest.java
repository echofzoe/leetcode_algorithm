package JavaSE_basic.day10;

public class InnerClassTest {
    public static void main(String[] args) {
        // 实例化静态成员内部类
        Person.FlightAttendant f1 = new Person.FlightAttendant();
        f1.eat();

        // 实例化非静态成员内部类
        Person p1 = new Person();
        Person.Teacher t1 = p1.new Teacher();
        t1.eat();
        t1.getName("形参");

        // 局部内部类1测试
        System.out.println(p1.method().getClass());   // 输出"class Person$1A"，格式为"外部类$数字+内部类名"
    }
}

class Person {
    String name = "人";

    public void eat() {
        System.out.println("人吃饭");
    }

    // 成员内部类 - 静态
    static class FlightAttendant {
        public void eat() {
            // Person.this.eat();   // 不能调用外部类的非静态方法
            System.out.println("空姐吃饭\n");
        }
    }

    // 成员内部类 - 非静态
    class Teacher {
        String name = "老师";

        public void eat() {
            Person.this.eat();   // 调用外部类的非静态属性
            System.out.println("老师吃饭\n");
        }

        public void getName(String name) {
            System.out.println(name);   // 方法的形参
            System.out.println(this.name);   // 内部类的属性
            System.out.println(Person.this.name);   // 外部类的属性
            System.out.println();
        }
    }

    public Person method() {
        // final int num = 10;   // 正常写法
        int num = 10;   // 省略了 final

        // 局部内部类1
        class A extends Person {
            String name = "A";

            public void show() {
                // num = 20;   // 错误，此变量其实被声明成了 final
                System.out.println(num + '\n');
            }
        }
        // 局部内部类的属性和方法只能在声明这个局部内部类的方法里调用
        System.out.println(new A().name);
        new A().show();
        return new A();
    }

    {
        // 局部内部类2
        class B {
            ;
        }
    }

    public Person() {
        // 局部内部类3
        class C {
            ;
        }
    }
}
