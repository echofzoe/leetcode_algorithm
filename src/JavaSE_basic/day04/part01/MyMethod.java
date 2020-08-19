package JavaSE_basic.day04.part01;

public class MyMethod {
    private int mPrivate;
    int mDefault;
    protected int mProtected;
    public int mPublic;

    private void methodPrivate() {
        mPrivate = 1;
        mDefault = 1;
        mProtected = 1;
        mPublic = 1;
    }

    void methodDefault() {
        mPrivate = 2;
        mDefault = 2;
        mProtected = 2;
        mPublic = 2;
    }

    protected void methodProtected() {
        mPrivate = 3;
        mDefault = 3;
        mProtected = 3;
        mPublic = 3;
    }

    public void methodPublic() {
        mPrivate = 4;
        mDefault = 4;
        mProtected = 4;
        mPublic = 4;
    }
}
