package JavaSE_basic.basic07.InstanceofTest;

public class Person {
    public String name;
    int age;

    public void description() {
        System.out.println("我是个人类");
    }

    public void myMethod() {
        System.out.println("这是 Peroson 类独有的方法");
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj instanceof Person){
            Person p = (Person)obj;
            if(p.name == ((Person) obj).name)
                return true;
        }

        return false;
    }
}
