package com.misaka.task;

import com.misaka.config.WsInit;
import com.misaka.enums.WsConnectStatus;
import com.misaka.service.MisakaWsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xiamo
 * @Description:
 * @ClassName: Heartbeat
 * @date 2021/12/1 10:54
 */
@Slf4j
@Component
public class Heartbeat {

    @Scheduled(fixedDelay = 5000)
    public void task(){
        if(WsInit.CStatus.equals(WsConnectStatus.CONNECTED)){
            String data = "{\"op\":1,\"d\":" + WsInit.last + "}";
            // log.info("send msg: " + data);
            MisakaWsService.wsService.send(data);
        }
    }

}
