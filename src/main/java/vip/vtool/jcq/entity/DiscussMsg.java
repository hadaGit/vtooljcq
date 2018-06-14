package vip.vtool.jcq.entity;

/**
 * @author lwd
 * @date 2018/6/14 16:12
 */
public class DiscussMsg{

    private int subType;
    private int msgId;
    private long fromDiscuss;
    private long fromQQ;
    private String msg;
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
