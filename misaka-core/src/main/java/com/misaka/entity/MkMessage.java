package com.misaka.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 频道消息表(MkMessage)实体类
 *
 * @author xiamo
 * @since 2021-12-03 15:18:39
 */
public class MkMessage implements Serializable {
    private static final long serialVersionUID = 890170446517898823L;
    /**
     * id
     */
    private Integer id;
    /**
     * 消息体（json，频道返回的原始数据）
     */
    private String msgBogy;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 频道id
     */
    private String guildId;
    /**
     * 子频道id
     */
    private String channelId;
    /**
     * msg_id，回复时需要用到
     */
    private String msgId;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgBogy() {
        return msgBogy;
    }

    public void setMsgBogy(String msgBogy) {
        this.msgBogy = msgBogy;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}