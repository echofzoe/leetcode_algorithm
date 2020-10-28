package JavaSE_advanced.advanced20_FunctionalInterface.lambdaMethodReferences;

/**
 * 构造方法的引用
 */
public class Lambda_构造方法的引用 {
    private static class Person {
        String name;
        int age;

        public Person() {
            System.out.println("Person类的无参构造方法执行了");
        }

        public Person(String name) {
            this.name = name;
            System.out.println("Person类的单参数的构造方法执行了");
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            System.out.println("Person类的两个参数的构造方法执行了");
        }
    }

    @FunctionalInterface
    private interface GetPersonWithNoneParameter {
        Person get();
    }

    @FunctionalInterface
    private interface GetPersonWithSingleParameter {
        Person get(String name);
    }

    @FunctionalInterface
    private interface GetPersonWithMultipleParameters {
        Person get(String name, int age);
    }

    public static void main(String[] args) {
        // 1. 使用lambda表达式，实现GetPersonWithNoneParameter接口
        GetPersonWithNoneParameter getPerson1 = Person::new;

        // 2. 使用lambda表达式，实现GetPersonWithSingleParameter接口
        GetPersonWithSingleParameter getPerson2 = Person::new;

        // 3. 使用lambda表达式，实现GetPersonWithMultipleParameters接口
        GetPersonWithMultipleParameters getPerson3 = Person::new;

        Person person1 = getPerson1.get();
        Person person2 = getPerson2.get("Jane");
        Person person3 = getPerson3.get("Jane", 18);
    }
}
