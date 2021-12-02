package com.misaka.service;

/**
 * @author xiamo
 * @Description: 消息处理
 * @ClassName: MessageHandle
 * @date 2021/12/2 15:48
 */
public interface MessageHandleService {

    /**
     * Handle.
     * 发送到消息队列中
     *
     * @param msg the msg
     */
    void sendMsg(String msg);

    /**
     * Handle.
     * 处理
     *
     * @param msg the msg
     */
    void handle(String msg);
}
