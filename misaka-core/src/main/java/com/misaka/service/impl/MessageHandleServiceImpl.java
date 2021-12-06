package com.misaka.service.impl;

import com.misaka.bili.anime.service.AnimeTimeLineService;
import com.misaka.bili.live.service.BiliLiveService;
import com.misaka.entity.MkMessage;
import com.misaka.entity.MkUser;
import com.misaka.enums.MsgType;
import com.misaka.handle.MsgProducer;
import com.misaka.service.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    @Autowired
    private MkUserService mkUserService;

    @Autowired
    private MkMessageService mkMessageService;

    @Autowired
    private BiliLiveService biliLiveService;

    @Autowired
    private WeatherHisService weatherHisService;

    @Autowired
    private HitokotoService hitokotoService;

    @Override
    public void sendMsg(String msg) {
        msgProducer.sendMsg(msg);
    }

    @Override
    public void handle(String msg) {
        JSONObject object = JSONObject.fromObject(msg);
        log.warn("处理消息中...");
        JSONObject msgBody = JSONObject.fromObject(object.get("d"));
        // 处理消息发送
        msgHandle(msgBody);

        // 处理@御坂的用户
        userHadle(msgBody);

        // 处理消息记录，将消息保存到mysql
        msgDbHandle(msgBody);
    }

    /**
     * Msg db handle.
     *
     * @param msgBody the msg body
     */
    @Async
    public void msgDbHandle(JSONObject msgBody) {
        MkMessage message = new MkMessage();
        message.setGuildId(msgBody.get("guild_id").toString());
        message.setChannelId(msgBody.get("channel_id").toString());
        message.setMsgBogy(msgBody.toString());
        message.setMsgId(msgBody.get("id").toString());
        message.setMsgContent(msgBody.get("content").toString());
        mkMessageService.insert(message);
    }

    /**
     * User hadle.
     *
     * @param msgBody the msg body
     */
    @Async
    public void userHadle(JSONObject msgBody) {
        JSONObject atBy = JSONObject.fromObject(msgBody.get("author"));
        MkUser user = new MkUser();
        user.setUserId(atBy.get("id").toString());
        user.setUserNick(atBy.get("username").toString());
        user.setUserAvatar(atBy.get("avatar").toString());
        user.setFirstAtGuildId(msgBody.get("guild_id").toString());
        user.setFirstAtChannelId(msgBody.get("channel_id").toString());
        mkUserService.insertOrUpdate(user);
    }

    /**
     * Msg handle.
     *
     * @param msgBody the msg body
     */
    @Async
    public void msgHandle(JSONObject msgBody) {
        // 内容
        String content = msgBody.get("content").toString();
        if (content.endsWith(MsgType.ANIME_TIMELINE.getText())) {
            // B站连载中的动漫
            sendBiliAnimeTimeLine(msgBody);
        } else if (content.contains(MsgType.LIVE_ADD.getText())) {
            biliLiveAdd(msgBody);
        } else if(content.equals(MsgType.JIA_RAN.getText())){

        } else if(content.contains(MsgType.WEATHER.getText())){
            // 查询天气
            weather(msgBody);
        } else if(content.endsWith(MsgType.HITOKOTO.getText())){
            // 一言
            hitokoto(msgBody);
        }
        else {
            randomMsg(msgBody);
        }
    }

    /**
     * Hitokoto.
     *
     * @param msgBody the msg body
     */
    private void hitokoto(JSONObject msgBody){
       String res = hitokotoService.queryHitokoto();
        MsgSendService.sendMsgTo(msgBody.get("channel_id").toString(), msgBody.get("id").toString(), res);
    }

    /**
     * Weather.
     */
    private void weather(JSONObject msgBody){
        String[] content = msgBody.get("content").toString().split("-");
        JSONObject user = JSONObject.fromObject(msgBody.get("author"));
        String res = weatherHisService.weather(content[1],user.get("id").toString(), msgBody.get("id").toString());
        MsgSendService.sendMsgTo(msgBody.get("channel_id").toString(), msgBody.get("id").toString(), res);
    }

    /**
     * Send bili anime time line.
     *
     * @param msgBody the msg body
     */
    private void sendBiliAnimeTimeLine(JSONObject msgBody) {
        String res = AnimeTimeLineService.selectAnimeTimeLine();
        MsgSendService.sendMsgTo(msgBody.get("channel_id").toString(), msgBody.get("id").toString(), res);
    }

    /**
     * Bili live add.
     *
     * @param msgBody the msg body
     */
    private void biliLiveAdd(JSONObject msgBody) {
        String roomId = "";
        String nick = "";
        String[] content = msgBody.get("content").toString().split("-");
        roomId = content[2];
        if (content.length > 3) {
            nick = content[3];
        }
        String msg = biliLiveService.addLive(roomId, nick);
        MsgSendService.sendMsgTo(msgBody.get("channel_id").toString(), msgBody.get("id").toString(), msg);
    }

    /**
     * Random msg.
     *
     * @param msgBody the msg body
     */
    private void randomMsg(JSONObject msgBody) {
        MsgSendService.sendMsgTo(msgBody.get("channel_id").toString(), msgBody.get("id").toString(), "啊咧，居然被你发现了！！！御坂御坂惊讶道。");
    }



}
