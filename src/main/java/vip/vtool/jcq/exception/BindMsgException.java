package vip.vtool.jcq.exception;

/**
 * 消息转发运行时异常
 *
 * @author biezhi
 * @date 2018/1/19
 */
public class BindMsgException extends RuntimeException {

    public BindMsgException() {
    }

    public BindMsgException(String message) {
        super(message);
    }

    public BindMsgException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindMsgException(Throwable cause) {
        super(cause);
    }
}
