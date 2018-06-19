package vip.vtool.jcq.entity;

/**
 * 好友添加消息
 * @author lwd
 * @date 2018/6/14 17:17
 */
public class FriendAdd {
    /**
     * 子类型，目前固定为1
     */
    private int subType;
    /**
     * 发送时间(时间戳)
     */
    private int sendTime;
    /**
     * 来源QQ
     */
    private long fromQQ;

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

    public long getFromQQ() {
        return fromQQ;
    }

    public void setFromQQ(long fromQQ) {
        this.fromQQ = fromQQ;
    }
}
