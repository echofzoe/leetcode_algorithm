package interview;

import org.junit.Test;

public class String对象创建 {

    @Test
    public void test1() {
        // - new String() 产生几个对象测试


        String str = new String("aaa");  // String str = new String("aaa") 共创建了两个对象，首先是在字符串常量池中创建了一个"aaa"，然后开辟了一块堆内存指向池中"aaa"的地址，再将堆内存地址赋给变量 str
        String afterStr = "aaa";

        // 虽然 str 和 afterStr 不是一个对象，但 str 的底层数组和 afterStr 是相同的对象
        System.out.println(str + afterStr);  // 在这条输出语句处打个断点，会发现 str 和 afterStr 的 value 字段，也就是 String 底层的 char[] （JDK14底层是 byte[]） 地址是相同的


        String beforeStr1 = "bbb";  // 如果先使用字面量的形式创建 beforeStr1 的对象，那么JVM会先去字符串常量池创建一个"bbb"对象，并将地址赋给变量 beforeStr1
        String str1 = new String("bbb");  // 再执行 String str1 = new String("bbb")，因为池中已有"bbb"，故 str1 会指向堆中新开辟的一块内存，堆中内存再指向池中的"aaa"，就不用再自己创建一个池中的"aaa"了

        System.out.println(beforeStr1 + str1);
        System.out.println("beforeStr1 的内存地址为: " + System.identityHashCode(beforeStr1) + ", str1.intern() 的内存地址为: " + System.identityHashCode(str1.intern()));
    }

    @Test
    public void test2() {
        // 1.在字符串常量池中创建对象
        String s1 = "abc";  // 先去字符串常量池中查找是否存在"abc"的实例,不存在时,会在字符串常量池中直接创建一个"abc"实例并返回引用给栈上的s1

        String s2 = "abc";  // 由于字符串常量池中已有"abc",所以直接将字符串常量池中"abc"的引用返回给栈帧里的局部变量表中的变量s2
        System.out.println("s1 == s2 ? " + (s1 == s2));          // true,栈上引用的字符串常量池中的地址相同
        System.out.println("s1.equals(s2) ? " + s1.equals(s2));  // true,值相同


        // 2.在堆内存中创建对象
        // - String s = new String("abc") 最多创建了2个实例,类的加载和执行要分开来讲:
        // 类加载时,"abc"被创建并驻留在了字符串常量池中(如果先前加载中没有创建驻留过);
        // 类执行此句时,因为"abc"对应的String实例已经存在于字符串常量池中,所以JVM会将此实例的地址赋给堆中s开辟的空间,再将堆中这块空间的地址返回给栈上的变量s

        String s3 = new String("dfg");  // 当字符串常量池中没有"dfg"时,会先在常量池中创建一个"dfg"实例,再将常量池中"dfg"的地址赋给堆中新开辟的空间,再将堆中这块空间的地址返回给栈上的s3
        String s4 = new String("dfg");  // 字符串常量池中已有"dfg",JVM直接在堆中开辟一块空间并引用常量池中的"dfg",再将堆中这块空间的地址返回给栈上的s4
        System.out.println("s3 == s4 ? " + (s3 == s4));          // false,栈上引用的堆中的地址不同
        System.out.println("s3.equals(s4) ? " + s3.equals(s4));  // true,值相同

        String s5 = "a" + "b" + "c";  // 由于"a","b","c"都是常量,编译时,JVM直接存储它们的字面量而不是它们的引用,所以这条语句会被自动编译为 String s5 = "abc"
        System.out.println("s1 == s5 ? " + (s1 == s5));  // true,栈上引用的字符串常量池中的地址相同

        String s6 = "a", s7 = "bc";  // 变量存储的是常量池中的引用地址
        String s8 = s6 + s7;  // JVM会在堆中创建一个以s6为基础的StringBuilder对象,然后调用append()方法完成与s7的合并,之后会调用toString()方法在堆中创建一个String对象，并把这个String对象的引用赋给s8
        System.out.println("s1 == s8 ? " + (s1 == s8));  // false,引用不同
    }

    @Test
    public void test3() {
        // - intern() 方法:
        // JDK1.6 中，调用此方法，
        //   当字符串常量池中没有调用intern()的字符串对象时，JVM会将字符串的对象的实例复制一份放入字符串常量池，并返回池中对象的地址
        //   当字符串常量池中已有调用intern()的字符串对象时，会直接返回池中对象的地址
        // JDK1.7 及以后，调用此方法，
        //   当字符串常量池中没有调用intern()的字符串对象时，JVM会将字符串的对象的引用复制一份放入字符串常量池，并返回池中的引用地址
        //   当字符串常量池中已有调用intern()的字符串对象时，会直接返回池中对象的地址

        // JDK14测试
        String s1 = new String("aaa");
        String s2 = "aaa";
        System.out.println(s1 == s2);           // false
        System.out.println(s1.intern() == s2);  // true
    }

}
