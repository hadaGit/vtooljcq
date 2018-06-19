package vip.vtool.jcq.entity;

/**
 * 请求添加好友
 * @author lwd
 * @date 2018/6/14 17:20
 */
public class RequestAddFriend {
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
    /**
     * 附言
     */
    private String msg;
    /**
     * 反馈标识(处理请求用)
     */
    private String responseFlag;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponseFlag() {
        return responseFlag;
    }

    public void setResponseFlag(String responseFlag) {
        this.responseFlag = responseFlag;
    }
}
