package testdemo;

@TableAnnotation("db_phone")
public class Phone {

    @FieldAnnotation(columnName = "db_name", type = "varchar", length = 10)
    private String name;

    public Phone() {
        System.out.println("Phone()");
    }

    public Phone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                '}';
    }
}
