package vip.vtool.jcq.entity;

import com.sobte.cqp.jcq.event.JcqAppAbstract;
import vip.vtool.jcq.exception.BindMsgException;

import java.lang.reflect.Method;

/**
 * 消息执行器
 *
 * @author biezhi
 * @date 2018/1/20
 */
public class Invoke {

    private Method method;

    public Invoke(Method method) {
        this.method = method;
    }

    /**
     * 回调给客户端
     * @param bot
     * @param message
     * @param <T>
     */
    public <T extends JcqAppAbstract> int call(T bot, QQMessage message) {
        try {
            return (int) method.invoke(bot, message);
        } catch (Exception e) {
            throw new BindMsgException(e);
        }
    }

}
