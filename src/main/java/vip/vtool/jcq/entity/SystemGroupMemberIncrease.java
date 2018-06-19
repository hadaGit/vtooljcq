package vip.vtool.jcq.entity;

/**
 * 群成员增加
 * @author lwd
 * @date 2018/6/14 17:07
 */
public class SystemGroupMemberIncrease {
    /**
     * 子类型，1/管理员已同意 2/管理员邀请
     */
    private int subType;
    /**
     * 发送时间(时间戳)
     */
    private int sendTime;
    /**
     * 来源群号
     */
    private long fromGroup;
    /**
     * 操作者QQ(即管理员QQ)
     */
    private long fromQQ;
    /**
     * 被操作QQ(即加群的QQ)
     */
    private long beingOperateQQ;

    public long getFromQQ() {
        return fromQQ;
    }

    public void setFromQQ(long fromQQ) {
        this.fromQQ = fromQQ;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getSendTime() {
        return sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    public long getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(long fromGroup) {
        this.fromGroup = fromGroup;
    }

    public long getBeingOperateQQ() {
        return beingOperateQQ;
    }

    public void setBeingOperateQQ(long beingOperateQQ) {
        this.beingOperateQQ = beingOperateQQ;
    }
}
