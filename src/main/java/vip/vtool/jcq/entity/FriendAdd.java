package vip.vtool.jcq.entity;

/**
 * @author lwd
 * @date 2018/6/14 17:17
 */
public class FriendAdd {

    private int subType;
    private int sendTime;
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
