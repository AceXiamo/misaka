package com.misaka.bili.live.entity;

import lombok.Data;

/**
 * @author xiamo
 * @Description:
 * @ClassName: BiliLive
 * @date 2021/12/3 14:35
 */
@Data
public class BiliLiveUser {

    /**
     * The Roomid.
     * 直播房间号
     */
    private String roomid;
    /**
     * The Uid.
     * 用户B站Uid
     */
    private String uid;
    /**
     * The Uname.
     * 用户B站昵称
     */
    private String uname;
    private String verify;
    private String virtual;
    /**
     * The Cover.
     * 直播实时截图
     */
    private String cover;
    private String live_time;
    private String round_status;
    /**
     * The On flag.
     * 直播状态 0：直播中  1：未开播
     */
    private String on_flag;
    /**
     * The Title.
     * 直播标题
     */
    private String title;
    private String tags;
    private String lock_status;
    private String hidden_status;
    /**
     * The User cover.
     * 直播间封面图
     */
    private String user_cover;
    private String short_id;
    private String online;
    private String area;
    private String area_v2_id;
    private String area_v2_parent_id;
    private String attentions;
    /**
     * The Background.
     * 直播间背景图
     */
    private String background;
    private String room_silent;
    private String room_shield;
    private String try_time;
    /**
     * The Area v 2 name.
     * 分区名
     */
    private String area_v2_name;
    /**
     * The First live time.
     * 首次开播时间
     */
    private Integer first_live_time;
    /**
     * The Live status.
     * 还是直播状态  0：封禁  1：直播中   2：未开播
     */
    private Integer live_status;

}
