package JavaSE_basic.day04.part02;

import JavaSE_basic.day04.part01.*;

public class AuthorityTest {
    public static void main(String[] args) {
        MyMethod mm = new MyMethod();

        //mm.mPrivate = 6;
        //mm.mDefault = 6;
        //mm.mProtected = 6;
        mm.mPublic = 6;
        System.out.println(mm.mPublic);   // 6

        //mm.methodPrivate();
        //mm.methodDefault();
        mm.methodPublic();
        System.out.println(mm.mPublic);   // 4

        // 测试不同包的子类 SubMyMethod
        SubMyMethod smm = new SubMyMethod();
        smm.MyMethod();
        System.out.println(smm.getmProtected());   // 0
    }
}
