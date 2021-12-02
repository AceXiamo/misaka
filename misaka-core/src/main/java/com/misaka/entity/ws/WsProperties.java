package com.misaka.entity.ws;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiamo
 * @Description: \
 * @ClassName: WsProperties
 * @date 2021/11/30 17:07
 */
@Data
@Builder
public class WsProperties{
    private String os;
    private String browser;
    private String device;

    public static WsProperties build(String os, String browser, String device){
        return WsProperties.builder()
                .os(os)
                .browser(browser)
                .device(device)
                .build();
    }
}