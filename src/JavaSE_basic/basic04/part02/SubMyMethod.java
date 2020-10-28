package JavaSE_basic.basic04.part02;

import JavaSE_basic.basic04.part01.MyMethod;

public class SubMyMethod extends MyMethod {

    public void MyMethod() {
        // 测试不同包的子类也可访问 protected
        this.mProtected = 0;
        this.mPublic = 2;
    }

    public int getmProtected(){
        return this.mProtected;
    }
}
