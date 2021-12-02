package com.misaka.enums;

import lombok.Getter;

/**
 * The enum Ws connect status.
 *
 * @author xiamo
 * @Description:
 * @ClassName: WsConnectStatus
 * @date 2021 /12/1 11:01
 */
@Getter
public enum WsOpCodeType {

    /**
     * opcode
     */
    DISPATCH("0"),
    HEARTBEAT("1"),
    IDENTIFY("2"),
    RESUME("6"),
    RECONNECT("7"),
    INVALID_SESSION("9"),
    HELLO("10"),
    HEARTBEAT_ACK("11"),
    ;

    /**
     * The Code.
     */
    private String code;

    WsOpCodeType(String code){
        this.code = code;
    }

}
