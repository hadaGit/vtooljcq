package vip.vtool.jcq.entity;

/**
 * 私聊消息
 * @author lwd
 * @date 2018/6/14 11:35
 */
public class PrivateMsg {
    /**
     * 消息ID
     */
    private int msgId;
    /**
     * 子类型，11/来自好友 1/来自在线状态 2/来自群 3/来自讨论组
     */
    private int subType;
    /**
     * 来源QQ
     */
    private long fromQQ;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息内容
     */
    private int font;

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
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


    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }
}
