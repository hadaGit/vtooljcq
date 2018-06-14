package vip.vtool.jcq.annotation;

import com.sobte.cqp.jcq.event.IType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定消息监听注解
 * <p>
 * 用于在机器人实现类中实现某类消息的回调
 *
 * @author biezhi
 * @date 2018/1/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bind {

    /**
     * 接受聊天的消息类型
     *
     * @return
     */
    int[] msgType() default {IType.EVENT_PrivateMsg};


}