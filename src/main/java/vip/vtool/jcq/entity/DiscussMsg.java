package vip.vtool.jcq.entity;

/**
 * 讨论组消息
 * @author lwd
 * @date 2018/6/14 16:12
 */
public class DiscussMsg{
    /**
     * 子类型，目前固定为1
     */
    private int subType;
    /**
     * 消息ID
     */
    private int msgId;
    /**
     * 来源讨论组
     */
    private long fromDiscuss;
    /**
     * 来源QQ号
     */
    private long fromQQ;
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

    public long getFromDiscuss() {
        return fromDiscuss;
    }

    public void setFromDiscuss(long fromDiscuss) {
        this.fromDiscuss = fromDiscuss;
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

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }
}
