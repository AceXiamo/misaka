package com.misaka.config;

import com.misaka.enums.WsConnectStatus;

/**
 * The type Ws init.
 *
 * @author xiamo
 * @Description:
 * @ClassName: WsInit
 * @date 2021 /12/1 10:58
 */
public class WsInit {

    /**
     * The constant last.
     * 记录最后一次返回中的t值
     */
    public static String last = null;

    /**
     * The constant CStatus.
     * Ws连接状态
     */
    public static WsConnectStatus CStatus = WsConnectStatus.DISCONNECT;

    public static final String WS_READY = "READY";
    public static final String AT_MESSAGE_CREATE = "AT_MESSAGE_CREATE";
}
