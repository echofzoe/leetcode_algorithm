package JavaSE_basic.day11;

public class MyExceptionTest {
    public static void main(String[] args) {
        int num = -1;
        if (num < 0) {
            throw new MyException("越界异常");
        } else {
            System.out.println(num);
        }
    }
}

class MyException extends RuntimeException {

    static final long serialVersionUID = -7134897190745766939L;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
