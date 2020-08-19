package JavaSE_advanced.day16_Reflect;

public class Person {
    private String name = "default";
    private int age = 1;
    public boolean isMale = true;

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show() {
        String sex = isMale ? "male" : "female";
        System.out.println("name = " + name + ", age = " + age + ", sex = " + sex);
    }

    private void showNation(String nation) {
        System.out.println("Nation is " + nation);
    }

    public void showCity(String city) {
        System.out.println("City is " + city);
    }
}
