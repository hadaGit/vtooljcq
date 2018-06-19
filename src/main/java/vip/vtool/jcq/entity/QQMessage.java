package vip.vtool.jcq.entity;

import java.io.Serializable;

/**
 * 事件、消息回调实体
 * @author lwd
 * @date 2018/6/14 9:35
 */
public class QQMessage implements Serializable {
    private static final long serialVersionUID = 6217085048244367214L;
    /**
     * 消息类型
     */
    private int msgType;
    /**
     * 私聊消息
     */
    private PrivateMsg privateMsg;
    /**
     * 群组消息
     */
    private GroupMsg groupMsg;
    /**
     * 讨论组消息
     */
    private DiscussMsg discussMsg;
    /**
     * 群文件上传
     */
    private GroupUpload groupUpload;
    /**
     * 群成员增加
     */
    private SystemGroupMemberIncrease systemGroupMemberIncrease;

    /**
     * 群成员减少
     */
    private SystemGroupMemberDecrease systemGroupMemberDecrease;
    /**
     * 添加好友时间
     */
    private FriendAdd friendAdd;
    /**
     * 请求添加好友
     */
    private RequestAddFriend requestAddFriend;
    /**
     * 请求添加群
     */
    private RequestAddGroup requestAddGroup;

    /**
     * 群成员变动
     */
    private SystemGroupAdmin systemGroupAdmin;

    public RequestAddGroup getRequestAddGroup() {
        return requestAddGroup;
    }

    public void setRequestAddGroup(RequestAddGroup requestAddGroup) {
        this.requestAddGroup = requestAddGroup;
    }

    public RequestAddFriend getRequestAddFriend() {
        return requestAddFriend;
    }

    public void setRequestAddFriend(RequestAddFriend requestAddFriend) {
        this.requestAddFriend = requestAddFriend;
    }

    public FriendAdd getFriendAdd() {
        return friendAdd;
    }

    public void setFriendAdd(FriendAdd friendAdd) {
        this.friendAdd = friendAdd;
    }

    public SystemGroupMemberIncrease getSystemGroupMemberIncrease() {
        return systemGroupMemberIncrease;
    }

    public void setSystemGroupMemberIncrease(SystemGroupMemberIncrease systemGroupMemberIncrease) {
        this.systemGroupMemberIncrease = systemGroupMemberIncrease;
    }

    public SystemGroupMemberDecrease getSystemGroupMemberDecrease() {
        return systemGroupMemberDecrease;
    }

    public void setSystemGroupMemberDecrease(SystemGroupMemberDecrease systemGroupMemberDecrease) {
        this.systemGroupMemberDecrease = systemGroupMemberDecrease;
    }

    public SystemGroupAdmin getSystemGroupAdmin() {
        return systemGroupAdmin;
    }

    public void setSystemGroupAdmin(SystemGroupAdmin systemGroupAdmin) {
        this.systemGroupAdmin = systemGroupAdmin;
    }

    public GroupUpload getGroupUpload() {
        return groupUpload;
    }

    public void setGroupUpload(GroupUpload groupUpload) {
        this.groupUpload = groupUpload;
    }

    public DiscussMsg getDiscussMsg() {
        return discussMsg;
    }

    public void setDiscussMsg(DiscussMsg discussMsg) {
        this.discussMsg = discussMsg;
    }

    public GroupMsg getGroupMsg() {
        return groupMsg;
    }

    public void setGroupMsg(GroupMsg groupMsg) {
        this.groupMsg = groupMsg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public PrivateMsg getPrivateMsg() {
        return privateMsg;
    }

    public void setPrivateMsg(PrivateMsg privateMsg) {
        this.privateMsg = privateMsg;
    }
}
