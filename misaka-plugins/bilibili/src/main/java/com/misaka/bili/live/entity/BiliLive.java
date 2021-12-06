package com.misaka.bili.live.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * B站直播表(BiliLive)实体类
 *
 * @author xiamo
 * @since 2021-12-03 16:24:35
 */
public class BiliLive implements Serializable {
    private static final long serialVersionUID = 993330241550523931L;
    /**
     * id
     */
    private Integer id;
    /**
     * b站直播房间号
     */
    private Integer biliLiveRoomId;
    /**
     * 主播的昵称/绰号（字符串用‘，’隔开）
     */
    private String nick;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBiliLiveRoomId() {
        return biliLiveRoomId;
    }

    public void setBiliLiveRoomId(Integer biliLiveRoomId) {
        this.biliLiveRoomId = biliLiveRoomId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}