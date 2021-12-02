package com.misaka.handle.receiver;

import com.misaka.config.RabbitConfig;
import com.misaka.utils.MqUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MsgReceiver
 * @date 2021/12/2 16:18
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_AT_MESSAGE)
public class MsgReceiver {

    @RabbitHandler
    public void process(String content) {
        MqUtil.handle(content);
    }

}
