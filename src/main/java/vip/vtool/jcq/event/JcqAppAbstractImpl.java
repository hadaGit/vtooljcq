package vip.vtool.jcq.event;


import com.sobte.cqp.jcq.entity.ICQVer;
import com.sobte.cqp.jcq.entity.IMsg;
import com.sobte.cqp.jcq.entity.IRequest;
import com.sobte.cqp.jcq.event.IType;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import vip.vtool.jcq.annotation.Bind;
import vip.vtool.jcq.entity.*;
import vip.vtool.jcq.exception.BindMsgException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象jcq实现
 * @author lwd
 * @date 2018/6/14 9:27
 */
public abstract class JcqAppAbstractImpl extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    /**
     * 注解绑定的函数映射
     */
    private final Map<Integer, List<Invoke>> mapping = new HashMap<>(8);

    private String appInfo;

    public JcqAppAbstractImpl(){
        System.out.println("------------appinfo 自动读取中...---------");
        String appDirectory = CQ.getAppDirectory();
        int startIndex = appDirectory.substring(0,appDirectory.length() - 1).lastIndexOf("\\");
        int endIndex = appDirectory.lastIndexOf("\\");
        appInfo = CQAPIVER + "," + appDirectory.substring(startIndex + 1,endIndex);
        System.out.println("appinfo = " + appInfo);
        init();
    }

    @Override
    public String appInfo() {
        return appInfo;
    }

    /**
     * 初始化 扫描
     */
    private void init(){
        JcqAppAbstract.CQ.logDebug(JcqAppAbstractImpl.class.getName(),"开始初始化 注解");
        Method[] methods = JcqAppAbstractImpl.class.getMethods();
        for (Method method : methods) {
            Bind bind = method.getAnnotation(Bind.class);
            JcqAppAbstract.CQ.logDebug(JcqAppAbstractImpl.class.getName(),"查找到方法 "+ method.getName());
            if (null == bind) {
                continue;
            }
            JcqAppAbstract.CQ.logDebug(JcqAppAbstractImpl.class.getName(),"查找到bind: "+ bind.msgType());
            if (method.getParameterTypes().length != 1) {
                throw new BindMsgException("方法 " + method.getName() + " 参数个数不对，请检查");
            }
            if (!method.getParameterTypes()[0].equals(QQMessage.class)) {
                throw new BindMsgException("方法 " + method.getName() + " 参数类型不对，请检查");
            }
            int[] msgTypes = bind.msgType();
            for (int msgType : msgTypes) {
                switch (msgType){
                    case IType.EVENT_Enable:
                    case IType.EVENT_Exit:
                    case IType.EVENT_Disable:
                    case IType.EVENT_Startup:
                        break;
                    default:
                        Class<?> returnType = method.getReturnType();
                        if(!returnType.toString().equals("int")){
                            throw new BindMsgException("方法 " + JcqAppAbstractImpl.class.getPackage().toString() + "." +
                            JcqAppAbstractImpl.class.getName() + "." + method.getName() + " 返回值不对[非 int]，请检查");
                        }
                }
                JcqAppAbstract.CQ.logDebug(JcqAppAbstractImpl.class.getName(),"添加方法 类型" + msgType + method.getName());
                List<Invoke> invokes = mapping.get(msgType);
                if (null == mapping.get(msgType)) {
                    invokes = new ArrayList<>();
                }
                invokes.add(new Invoke(method));
                mapping.put(msgType, invokes);
            }
            JcqAppAbstract.CQ.logInfo(JcqAppAbstractImpl.class.getName(),String.format("绑定消息监听函数 [%s] => %s", method.getName(), Arrays.toString(msgTypes)));
        }
    }

    @Override
    public int startup() {
        List<Invoke> invokes = mapping.get(IType.EVENT_Startup);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Startup);
                invoke.call(this, qqMessage);
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int exit() {
        List<Invoke> invokes = mapping.get(IType.EVENT_Exit);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Exit);
                invoke.call(this, qqMessage);
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int enable() {
        List<Invoke> invokes = mapping.get(IType.EVENT_Enable);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Enable);
                invoke.call(this, qqMessage);
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int disable() {
        List<Invoke> invokes = mapping.get(IType.EVENT_Disable);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Disable);
                invoke.call(this, qqMessage);
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
        List<Invoke> invokes = mapping.get(IType.EVENT_PrivateMsg);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_PrivateMsg);
                PrivateMsg privateMsg = new PrivateMsg();
                privateMsg.setMsgId(msgId);
                privateMsg.setMsg(msg);
                privateMsg.setFont(font);
                privateMsg.setFromQQ(fromQQ);
                qqMessage.setPrivateMsg(privateMsg);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {
        List<Invoke> invokes = mapping.get(IType.EVENT_GroupMsg);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_GroupMsg);
                GroupMsg groupMsg = new GroupMsg();
                groupMsg.setMsgId(msgId);
                groupMsg.setFont(font);
                groupMsg.setFromGroup(fromGroup);
                groupMsg.setFromAnonymous(fromAnonymous);
                groupMsg.setMsg(msg);
                groupMsg.setFromQQ(fromQQ);
                qqMessage.setGroupMsg(groupMsg);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int discussMsg(int subType, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
        List<Invoke> invokes = mapping.get(IType.EVENT_DiscussMsg);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_DiscussMsg);
                DiscussMsg discussMsg = new DiscussMsg();
                discussMsg.setSubType(subType);
                discussMsg.setMsgId(msgId);
                discussMsg.setFromDiscuss(fromDiscuss);
                discussMsg.setFromQQ(fromQQ);
                discussMsg.setMsg(msg);
                discussMsg.setFont(font);
                qqMessage.setDiscussMsg(discussMsg);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
        List<Invoke> invokes = mapping.get(IType.EVENT_GroupUpload);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_GroupUpload);
                GroupUpload groupUpload = new GroupUpload();
                groupUpload.setSubType(subType);
                groupUpload.setSendTime(sendTime);
                groupUpload.setFromGroup(fromGroup);
                groupUpload.setFromQQ(fromQQ);
                groupUpload.setFile(file);
                qqMessage.setGroupUpload(groupUpload);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int groupAdmin(int subType, int sendTime, long fromGroup, long beingOperateQQ) {
        List<Invoke> invokes = mapping.get(IType.EVENT_System_GroupAdmin);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_System_GroupAdmin);
                SystemGroupAdmin systemGroupAdmin = new SystemGroupAdmin();
                systemGroupAdmin.setSubType(subType);
                systemGroupAdmin.setSendTime(sendTime);
                systemGroupAdmin.setFromGroup(fromGroup);
                systemGroupAdmin.setBeingOperateQQ(beingOperateQQ);
                qqMessage.setSystemGroupAdmin(systemGroupAdmin);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int groupMemberDecrease(int subType, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        List<Invoke> invokes = mapping.get(IType.EVENT_System_GroupMemberDecrease);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_System_GroupMemberDecrease);
                SystemGroupMemberDecrease systemGroupMemberDecrease = new SystemGroupMemberDecrease();
                systemGroupMemberDecrease.setSubType(subType);
                systemGroupMemberDecrease.setSendTime(sendTime);
                systemGroupMemberDecrease.setFromGroup(fromGroup);
                systemGroupMemberDecrease.setFromQQ(fromQQ);
                systemGroupMemberDecrease.setBeingOperateQQ(beingOperateQQ);
                qqMessage.setSystemGroupMemberDecrease(systemGroupMemberDecrease);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int groupMemberIncrease(int subType, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        List<Invoke> invokes = mapping.get(IType.EVENT_System_GroupMemberDecrease);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_System_GroupMemberDecrease);
                SystemGroupMemberIncrease systemGroupMemberIncrease = new SystemGroupMemberIncrease();
                systemGroupMemberIncrease.setSubType(subType);
                systemGroupMemberIncrease.setSendTime(sendTime);
                systemGroupMemberIncrease.setFromGroup(fromGroup);
                systemGroupMemberIncrease.setBeingOperateQQ(beingOperateQQ);
                qqMessage.setSystemGroupMemberIncrease(systemGroupMemberIncrease);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int friendAdd(int subType, int sendTime, long fromQQ) {
        List<Invoke> invokes = mapping.get(IType.EVENT_Friend_Add);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Friend_Add);
                FriendAdd friendAdd = new FriendAdd();
                friendAdd.setSubType(subType);
                friendAdd.setSendTime(sendTime);
                friendAdd.setFromQQ(fromQQ);
                qqMessage.setFriendAdd(friendAdd);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int requestAddFriend(int subType, int sendTime, long fromQQ, String msg, String responseFlag) {
        List<Invoke> invokes = mapping.get(IType.EVENT_Request_AddFriend);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Request_AddFriend);
                RequestAddFriend requestAddFriend = new RequestAddFriend();
                requestAddFriend.setSubType(subType);
                requestAddFriend.setSendTime(sendTime);
                requestAddFriend.setFromQQ(fromQQ);
                requestAddFriend.setMsg(msg);
                requestAddFriend.setResponseFlag(responseFlag);
                qqMessage.setRequestAddFriend(requestAddFriend);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }

    @Override
    public int requestAddGroup(int subType, int sendTime, long fromGroup, long fromQQ, String msg, String responseFlag) {
        List<Invoke> invokes = mapping.get(IType.EVENT_Request_AddGroup);
        if(invokes != null) {
            for (Invoke invoke : invokes) {
                QQMessage qqMessage = new QQMessage();
                qqMessage.setMsgType(IType.EVENT_Request_AddGroup);
                RequestAddGroup requestAddGroup = new RequestAddGroup();
                requestAddGroup.setSubType(subType);
                requestAddGroup.setSendTime(sendTime);
                requestAddGroup.setFromQQ(fromQQ);
                requestAddGroup.setMsg(msg);
                requestAddGroup.setResponseFlag(responseFlag);
                qqMessage.setRequestAddGroup(requestAddGroup);
                if (invoke.call(this, qqMessage) == IMsg.MSG_INTERCEPT) {
                    return IMsg.MSG_INTERCEPT;
                }
            }
        }
        return IMsg.MSG_IGNORE;
    }
}
