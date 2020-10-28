package JavaSE_advanced.advanced20_FunctionalInterface.lambdaMethodReferences;

/**
 * 对象方法的特殊引用
 */
public class Lambda_对象方法的特殊引用 {

    private static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @FunctionalInterface
    private interface MyInterface1 {
        String get(Person person);
    }

    @FunctionalInterface
    private interface MyInterface2 {
        void set(Person person, String name);
    }

    public static void main(String[] args) {
        Person jane = new Person();
        jane.setName("Jane");

        MyInterface1 lambda1 = x -> x.getName();
        // 上式可以简化成下式
        MyInterface1 lambda2 = Person::getName;

        System.out.println(lambda1.get(jane));
        System.out.println(lambda2.get(jane));
        
        System.out.println("------------分割线------------");

        MyInterface2 lambda3 = (x, s) -> x.setName(s);
        // 上式可以简化成下式
        MyInterface2 lambda4 = Person::setName;

        lambda3.set(jane, "Zoe");
        System.out.println(jane.getName());
        lambda4.set(jane, "Max");
        System.out.println(jane.getName());
    }
}
