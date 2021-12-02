package com.misaka.utils;

import com.misaka.service.MessageHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MqUtil
 * @date 2021/12/2 17:41
 */
@Component
public class MqUtil {

    @Autowired
    private MessageHandleService messageHandleService;

    public static MqUtil mqUtil;

    @PostConstruct
    public void init() {
        mqUtil = this;
    }

    public static void send(String s){
        mqUtil.messageHandleService.sendMsg(s);
    }

    public static void handle(String s){
        mqUtil.messageHandleService.handle(s);
    }

}
