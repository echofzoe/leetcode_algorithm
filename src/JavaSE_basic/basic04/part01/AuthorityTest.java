package JavaSE_basic.basic04.part01;

public class AuthorityTest {
    public static void main(String[] args) {
        MyMethod mm = new MyMethod();

        //mm.mPrivate = 6;
        mm.mDefault = 6;
        mm.mProtected = 6;
        mm.mPublic = 6;
        System.out.println(mm.mProtected);   // 6

        //mm.methodPrivate();
        mm.methodDefault();
        mm.methodProtected();
        System.out.println(mm.mDefault);   // 3
    }
}
