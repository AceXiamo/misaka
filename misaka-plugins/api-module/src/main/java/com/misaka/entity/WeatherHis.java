package com.misaka.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 天气查询记录表(WeatherHis)实体类
 *
 * @author xiamo
 * @since 2021-12-04 10:10:27
 */
public class WeatherHis implements Serializable {
    private static final long serialVersionUID = 396341576580121006L;
    /**
     * id
     */
    private String id;
    /**
     * 查询的城市
     */
    private String city;
    /**
     * 查询的时间
     */
    private Date time;
    /**
     * 查询的用户
     */
    private String userId;
    /**
     * 消息id
     */
    private String msgId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}