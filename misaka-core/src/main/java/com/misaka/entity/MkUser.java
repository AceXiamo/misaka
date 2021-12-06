package com.misaka.entity;

import lombok.Builder;

import java.util.Date;
import java.io.Serializable;

/**
 * 频道用户表(MkUser)实体类
 *
 * @author xiamo
 * @since 2021-12-03 15:19:50
 */
public class MkUser implements Serializable {
    private static final long serialVersionUID = 903469758208657935L;
    /**
     * id
     */
    private Integer id;
    /**
     * 频道中返回的用户id
     */
    private String userId;
    /**
     * 频道中返回的用户昵称
     */
    private String userNick;
    /**
     * 频道中返回的用户头像
     */
    private String userAvatar;
    /**
     * 首次@misaka的时间
     */
    private Date firstAtTime;
    /**
     * 首次@misaka时的频道id
     */
    private String firstAtGuildId;
    /**
     * 首次@misaka时的子频道id
     */
    private String firstAtChannelId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Date getFirstAtTime() {
        return firstAtTime;
    }

    public void setFirstAtTime(Date firstAtTime) {
        this.firstAtTime = firstAtTime;
    }

    public String getFirstAtGuildId() {
        return firstAtGuildId;
    }

    public void setFirstAtGuildId(String firstAtGuildId) {
        this.firstAtGuildId = firstAtGuildId;
    }

    public String getFirstAtChannelId() {
        return firstAtChannelId;
    }

    public void setFirstAtChannelId(String firstAtChannelId) {
        this.firstAtChannelId = firstAtChannelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}