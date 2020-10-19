package interview;

import org.junit.Test;

public class String对象创建 {

    @Test
    public void test() {
        // 在方法区中的字符串常量池中创建对象
        String s1 = "abc";    // 先去常量池中查找是否存在"abc",存在时直接返回常量池里的引用;不存在时,会在字符串常量池中创建一个对象并返回引用
        // - String s1 = new String(“abc”) 最多创建了2个实例,类的加载和执行要分开来讲:
        // - 当加载类时,"abc"被创建并驻留在了字符串常量池中(如果先前加载中没有创建驻留过)
        // - 当执行此句时,因为"abc"对应的String实例已经存在于字符串常量池中,所以JVM会将此实例复制到堆中并返回引用地址。
        String s2 = "abc";    // 同上,由于第一句已经在常量池中创建了,所以直接返回上句创建的对象的引用
        System.out.println("s1 == s2 result: " + (s1 == s2));    // true,引用相同
        System.out.println("s1.equals(s2) result: " + s1.equals(s2));    // true,值相同

        // 在堆内存中创建对象
        String s3 = new String("abc");    // 在堆(heap)中创建一个对象,当字符串常量池中没有"abc"时,会在常量池中也创建一个对象;当常量池中已经存在了，就不会创建新的了
        String s4 = new String("abc");    // 同上,在堆(heap)中创建一个对象,地址不同
        System.out.println("s3 == s4 result: " + (s3 == s4));    // false,引用地址不同
        System.out.println("s3.equals(s4) result: " + s3.equals(s4));    // true,值相同

        String s5 = "a" + "b" + "c";    // 由于"a","b","c"都是常量,编译时,会被自动编译为 String s5 = "abc"
        System.out.println("s1 == s5 result: " + (s1 == s5));    // true,引用相同

        String s6 = "a", s7 = "bc";    // 变量存储的是常量池中的引用地址
        String s8 = s6 + s7;    // JVM会在堆中创建一个以s6为基础的StringBuilder对象,然后调用append()方法完成与s7的合并,之后会调用toString()方法在堆中创建一个String对象，并把这个String对象的引用赋给s8
        System.out.println("s1 == s8 result: " + (s1 == s8));    // false,引用不同
    }

}
