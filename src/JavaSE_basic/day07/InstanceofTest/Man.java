package JavaSE_basic.day07.InstanceofTest;

public class Man extends Person {
    public String name;
    boolean isSmoking;

    public void description() {
        String info;

        if(isSmoking)
            info = "爱抽烟";
        else
            info = "不爱抽烟";

        System.out.println("我是个" + info + "的男人");
    }
}
