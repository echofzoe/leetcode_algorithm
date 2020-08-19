package JavaSE_basic.day08;

public class StaticTest {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle();

        System.out.println("c1的id为：" + c1.getId());
        System.out.println("c2的id为：" + c2.getId());

        System.out.println("已创建的圆的个数为：" + Circle.getTotal());

    }
}

class Circle {
    private double radius;
    private int id;

    public Circle() {
        id = init++;
        total++;
    }

    public Circle(double radius) {
        this();
        this.radius = radius;
    }

    private static int init = 101;
    private static int total;

    public double findArea() {
        return 3.14 * radius * radius;
    }

    public int getId() {
        return this.id;
    }

    public static int getTotal() {
        return total;
    }
}
