package com.misaka.service.Impl;

import com.bili.anime.service.AnimeTimeLineService;
import com.misaka.config.BotApi;
import com.misaka.handle.MsgProducer;
import com.misaka.service.MessageHandleService;
import com.misaka.utils.MisakaHttpUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MessageHandleServiceImpl
 * @date 2021/12/2 17:18
 */
@Slf4j
@Service("messageHandleService")
public class MessageHandleServiceImpl implements MessageHandleService {

    @Autowired
    MsgProducer msgProducer;

    @Override
    public void sendMsg(String msg){
        msgProducer.sendMsg(msg);
    }

    @Override
    public void handle(String msg){
        JSONObject object = JSONObject.fromObject(msg);
        log.info("处理消息中...");
        JSONObject msgBody = JSONObject.fromObject(object.get("d"));
        // @御坂 的 用户
        JSONObject atBy = JSONObject.fromObject(msgBody.get("author"));
        // 内容
        String content = msgBody.get("content").toString();
        if(content.endsWith("连载中")){
            log.info("查询B站中正在连载中的动漫...");
            sendBiliAnimeTimeLine(msgBody);
        } else {
            log.info("随机回复");
            randomMsg(msgBody);
        }
    }

    /**
     * Send bili anime time line.
     */
    private void sendBiliAnimeTimeLine(JSONObject msgBody){
        String res = AnimeTimeLineService.selectAnimeTimeLine();
        Map<String,String> params = new HashMap<>(1);
        params.put("{channel_id}", msgBody.get("channel_id").toString());
        JSONObject json = new JSONObject();
        json.put("msg_id",msgBody.get("id").toString());
        json.put("content",res);
        // post
        MisakaHttpUtils.sendJsonStr(BotApi.MESSAGE, params, json.toString());
    }

    private void randomMsg(JSONObject msgBody){
        Map<String,String> params = new HashMap<>(1);
        params.put("{channel_id}", msgBody.get("channel_id").toString());
        JSONObject json = new JSONObject();
        json.put("msg_id",msgBody.get("id").toString());
        json.put("content", "啊咧，居然被你发现了！！！御坂御坂惊讶道。");
        // post
        MisakaHttpUtils.sendJsonStr(BotApi.MESSAGE, params, json.toString());
    }

    /**
            {
                "op":0,
                "s":2,
                "t":"AT_MESSAGE_CREATE",
                "d":{
                    "author":{
                        "avatar":"http://thirdqq.qlogo.cn/g?b=oidb\u0026k=micXEULRv7rs3MWn9pibibDGQ\u0026s=100\u0026t=1635474399",
                        "bot":false,
                        "id":"12670057053635175509",
                        "username":"浅末saki"
                    },
                    "channel_id":"1617450",
                    "content":"\u003c@!15488460326014391322\u003e 连载",
                    "guild_id":"11183276686131878018",
                    "id":"08c3fb8c90d1a1c1970110aadc621a14313236373030353730353336333531373535303920801e2800308e87eed403383c403c48f1aea28d065800",
                    "member":{
                        "joined_at":"2021-11-30T12:00:37+08:00",
                        "roles":["1"]
                    },
                    "mentions":[{
                        "avatar":"http://thirdqq.qlogo.cn/g?b=oidb\u0026k=6WoNd6rl8UHCkib4RsAhJqg\u0026s=100\u0026t=1638245129",
                        "bot":true,
                        "id":"15488460326014391322",
                        "username":"御坂20001-测试中"
                    }],
                    "timestamp":"2021-12-02T17:52:49+08:00"
                }
            }
     */
}
