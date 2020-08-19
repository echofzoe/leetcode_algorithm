package project.dispatch_system.control.service;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName TeamException
 * @Description 自定义异常类
 * @date 2020/5/25 17:04
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387516993112759948L;

    public TeamException() {
    }

    public TeamException(String message) {
        super(message);
    }
}
