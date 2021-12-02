package com.misaka.config;

/**
 * The type Bot api.
 *
 * @author xiamo
 * @Description:
 * @ClassName: BotApi
 * @date 2021 /11/30 16:23
 */
public class BotApi {

    /**
     * The constant HOST.
     */
    public static final String HOST = "https://api.sgroup.qq.com";
    //    https://api.sgroup.qq.com
    //    https://sandbox.api.sgroup.qq.com

    /**
     * The constant WS.
     * 获取Ws连接地址
     */
    public static final String WS = HOST + "/gateway";

    /**
     * The constant WS_URL.
     * 用于保存拿到的Ws地址
     */
    public static String WS_URL = "";

    public static String MESSAGE = HOST + "/channels/{channel_id}/messages";
}
