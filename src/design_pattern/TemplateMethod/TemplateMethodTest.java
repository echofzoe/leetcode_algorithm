package design_pattern.TemplateMethod;

public class TemplateMethodTest {
    public static void main(String[] args) {
        Template t1 = new SubTemplate();
        t1.spendTime();
    }
}

abstract class Template {

    // 计算某段代码执行所花费的时间
    public void spendTime() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("此算法花费的时间为：" + (end - start));
    }

    public abstract void code();
}

class SubTemplate extends Template {
    @Override
    public void code() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < i; j++) {
                ;
            }
        }
    }
}