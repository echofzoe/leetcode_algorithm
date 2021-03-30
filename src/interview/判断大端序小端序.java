package interview;

public class 判断大端序小端序 {

    // 大端序：数字低位在地址高位，数字高位在地址低位
    // 小端序：数字低位在地址低位，数字高位在地址高位

    public static void main(String[] args) {
        // java 里，int 占 4 字节
        int a = 0x11223344;  // 0001 0001 0010 0010 0011 0011 0100 0100
        System.out.println(Integer.toBinaryString(a));

        // java 里，char 占 2 字节
        char b = (char) a;
        switch (b) {
            case 0x3344 -> System.out.println("java中字节存储的机制是小端序");
            case 0x1122 -> System.out.println("java中字节存储的机制是大端序");
            default -> System.out.println("程序有误");
        }
    }
}
