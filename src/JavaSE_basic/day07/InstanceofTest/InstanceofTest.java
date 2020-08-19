package JavaSE_basic.day07.InstanceofTest;

public class InstanceofTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.description();

        System.out.println();
        if(p1 instanceof Man){
            Man m1 = (Man)p1;
            m1.description();
            System.out.println("p1 对象是 Man 的实例");
        }
        else
            System.out.println("p1 对象不是 Man 的实例");

        System.out.println();
        Person p2 = new Man();
        if(p2 instanceof Man){
            Man m2 = (Man)p2;
            m2.description();
            System.out.println("p2 对象是 Man 的实例");
        }
        else
            System.out.println("p2 对象不是 Man 的实例");

        System.out.println();
        if(p2 instanceof Women){
            Women w1 = (Women)p2;
            w1.description();
            System.out.println("p2 对象是 Women 的实例");
        }
        else
            System.out.println("p2 对象不是 Women 的实例");

        System.out.println();
        // 注意，一个类只能和它的直接或间接父类转，而不能和它父类的其他子类转
        // 下面的写法等价于 Person p3 = new Women();
        Object obj = new Women();
        Person p3 = (Person)obj;
        p3.description();

        // 错误写法示例，编译能过，运行不能过
        // 举例1 - 转换成同级子类
//        Person p4 = new Women();
//        Man m4 = (Man)p4;

        // 举例2 - 父类转子类
//        Person p5 = new Person();
//        Man m5 = (Man)p5;
    }
}
