package JavaSE_basic.day07.InstanceofTest;

public class Women extends Person {
    boolean isBeauty;

    public void description() {
        String info;

        if(isBeauty)
            info = "漂亮";
        else
            info = "不漂亮";

        System.out.println("我是个" + info + "的女人");
    }
}
