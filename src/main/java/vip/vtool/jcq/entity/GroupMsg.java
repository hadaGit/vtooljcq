package vip.vtool.jcq.entity;

/**
 * 群消息
 * @author lwd
 * @date 2018/6/14 11:38
 */
public class GroupMsg {
    /**
     * 子类型，目前固定为1
     */
    private int subType;
    /**
     * 消息ID
     */
    private int msgId;
    /**
     * 来源群号
     */
    private long fromGroup;
    /**
     * 来源QQ号
     */
    private long fromQQ;
    /**
     * 来源匿名者
     */
    private String fromAnonymous;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 字体
     */
    private int font;

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public long getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(long fromGroup) {
        this.fromGroup = fromGroup;
    }

    public long getFromQQ() {
        return fromQQ;
    }

    public void setFromQQ(long fromQQ) {
        this.fromQQ = fromQQ;
    }

    public String getFromAnonymous() {
        return fromAnonymous;
    }

    public void setFromAnonymous(String fromAnonymous) {
        this.fromAnonymous = fromAnonymous;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }
}
