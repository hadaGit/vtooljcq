package vip.vtool.jcq.entity;

/**
 * 群管理员变动通知
 * @author lwd
 * @date 2018/6/14 17:02
 */
public class SystemGroupAdmin {
    /**
     * 子类型，1/被取消管理员 2/被设置管理员
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
     * 被操作QQ
     */
    private long beingOperateQQ;

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
