package com.misaka.service;

import com.misaka.config.BotApi;
import com.misaka.utils.MisakaHttpUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MsgSendService
 * @date 2021/12/3 16:47
 */
public class MsgSendService {

    /**
     * Send msg to.
     *
     * @param channelId the channel id
     * @param msgId     the msg id
     * @param msg       the msg
     */
    public static void sendMsgTo(String channelId, String msgId, String msg){
        Map<String, String> params = new HashMap<>(1);
        params.put("{channel_id}", channelId);
        JSONObject json = new JSONObject();
        json.put("msg_id", msgId);
        json.put("content", msg);
        // post
        MisakaHttpUtils.sendJsonStr(BotApi.MESSAGE, params, json.toString());
    }

}
