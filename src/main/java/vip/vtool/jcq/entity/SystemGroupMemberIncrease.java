package vip.vtool.jcq.entity;

/**
 * @author lwd
 * @date 2018/6/14 17:07
 */
public class SystemGroupMemberIncrease {

    private int subType;
    private int sendTime;
    private long fromGroup;
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
