package vip.vtool.jcq.entity;

/**
 * 群文件上传
 * @author lwd
 * @date 2018/6/14 16:40
 */
public class GroupUpload {
    /**
     * 子类型，目前固定为1
     */
    private int subType;
    /**
     * 发送时间(时间戳)// 10位时间戳
     */
    private int sendTime;
    /**
     * 来源群号
     */
    private long fromGroup;
    /**
     * 来源QQ号
     */
    private long fromQQ;
    /**
     * 上传文件信息
     */
    private String file;

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

    public long getFromQQ() {
        return fromQQ;
    }

    public void setFromQQ(long fromQQ) {
        this.fromQQ = fromQQ;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
