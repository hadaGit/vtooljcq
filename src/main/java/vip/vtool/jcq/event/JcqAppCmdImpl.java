package vip.vtool.jcq.event;

import com.sobte.cqp.jcq.event.JcqAppAbstract;
import vip.vtool.jcq.annotation.Bind;
import vip.vtool.jcq.annotation.Cmd;
import vip.vtool.jcq.entity.Invoke;
import vip.vtool.jcq.entity.QQMessage;
import vip.vtool.jcq.exception.BindMsgException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sobte.cqp.jcq.event.IType.*;

/**
 * @author lwd
 * @date 2018/6/29 14:05
 */
public class JcqAppCmdImpl extends JcqAppAbstractImpl {

    /**
     * 注解绑定的函数映射
     */
    private final Map<String, List<Invoke>> mapping = new HashMap<>(8);

    public JcqAppCmdImpl(){
        super();
        init();
    }

    private void init(){
        JcqAppAbstract.CQ.logDebug(JcqAppCmdImpl.class.getName(),"开始初始化 注解");
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            Cmd cmd = method.getAnnotation(Cmd.class);
            JcqAppAbstract.CQ.logDebug(JcqAppCmdImpl.class.getName(),"查找到方法 "+ method.getName());
            if (null == cmd) {
                continue;
            }
            JcqAppAbstract.CQ.logDebug(JcqAppCmdImpl.class.getName(),"查找到cmd: "+ cmd.value());
            if (method.getParameterTypes().length != 1) {
                throw new BindMsgException("方法 " + method.getName() + " 参数个数不对，请检查");
            }
            if (!method.getParameterTypes()[0].equals(QQMessage.class)) {
                throw new BindMsgException("方法 " + method.getName() + " 参数类型不对，请检查");
            }
            Class<?> returnType = method.getReturnType();
            if(!returnType.toString().equals("int")){
                throw new BindMsgException("方法 " + JcqAppCmdImpl.class.getPackage().toString() + "." +
                        JcqAppCmdImpl.class.getName() + "." + method.getName() + " 返回值不对[非 int]，请检查");
            }
            List<Invoke> invokes = mapping.get(cmd.value());
            if (null == mapping.get(cmd.value())) {
                invokes = new ArrayList<>();
            }
            invokes.add(new Invoke(method));
            mapping.put(cmd.value(), invokes);
            JcqAppAbstract.CQ.logInfo(JcqAppCmdImpl.class.getName(),String.format("绑定消息命令监听函数 [%s] => %s", method.getName(), cmd.value()));
        }
    }

    @Bind(msgType = {EVENT_PrivateMsg,EVENT_GroupMsg,EVENT_DiscussMsg})
    public int msgCmdRoute(QQMessage message){
        String msg = "";
        switch (message.getMsgType()){
            case EVENT_PrivateMsg:
                msg = message.getPrivateMsg().getMsg();
                break;
            case EVENT_GroupMsg:
                msg = message.getGroupMsg().getMsg();
                break;
            case EVENT_DiscussMsg:
                msg = message.getDiscussMsg().getMsg();
                break;
            default:
        }
        for (Map.Entry<String, List<Invoke>> stringListEntry : mapping.entrySet()) {
            String key = stringListEntry.getKey();
            boolean matches = msg.matches(key);
            if(matches){
                List<Invoke> invokes = stringListEntry.getValue();
                for (Invoke invoke : invokes) {
                    if(invoke.call(this,message) == MSG_INTERCEPT){
                        return MSG_INTERCEPT;
                    }
                }
            }
        }
        return MSG_IGNORE;
    }
}
