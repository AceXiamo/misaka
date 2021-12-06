package com.misaka.enums;

import lombok.Getter;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MsgType
 * @date 2021/12/3 15:29
 */
@Getter
public enum MsgType {

    /**
     * Anime timeline msg type.
     */
    ANIME_TIMELINE("连载中"),
    LIVE_ADD("live-add-"),
    JIA_RAN("嘉然\uD83E\uDD70\n"),
    WEATHER("天气-"),
    HITOKOTO("一言"),
    ;
    private String text;

    MsgType(String text){
        this.text = text;
    }

}
