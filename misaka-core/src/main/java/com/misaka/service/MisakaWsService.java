package com.misaka.service;

import com.misaka.config.BotApi;
import com.misaka.config.MisakaConfiguration;
import com.misaka.config.WsInit;
import com.misaka.entity.ws.WsAuth;
import com.misaka.entity.ws.WsAuthD;
import com.misaka.enums.WsConnectStatus;
import com.misaka.enums.WsOpCodeType;
import com.misaka.utils.MqUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * The type Misaka ws service.
 *
 * @author xiamo
 * @Description:
 * @ClassName: MisakaWsService
 * @date 2021 /11/30 16:18
 */
@Slf4j
public class MisakaWsService extends WebSocketClient {

    /**
     * The constant wsService.
     */
    public static MisakaWsService wsService;

    public MisakaWsService(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("WS连接已建立");
        // 修改连接状态
        WsInit.CStatus = WsConnectStatus.CONNECTED;
    }

    @Override
    public void onMessage(String s) {
        log.info("收到消息: " + s);
        // 转 JSONObject
        JSONObject obj = JSONObject.fromObject(s);
        // 取出 opcode
        String opcode = obj.get("op").toString();

        if (opcode.equals(WsOpCodeType.DISPATCH.getCode())) {
            // 服务端进行消息推送
            if (obj.has("t")) {
                // 记录最后一条推送的 t 值
                String last = obj.get("t").toString();
                if (!last.equals(WsInit.WS_READY)) {
                    WsInit.last = last;
                    if(last.equals(WsInit.AT_MESSAGE_CREATE)){
                        MqUtil.send(s);
                    }
                }
            }
        } else if (opcode.equals(WsOpCodeType.HEARTBEAT.getCode())) {
            // 客户端或服务端发送心跳
        } else if (opcode.equals(WsOpCodeType.RECONNECT.getCode())) {
            // 服务端通知客户端重新连接
        } else if (opcode.equals(WsOpCodeType.INVALID_SESSION.getCode())) {
            // 当identify或resume的时候，如果参数有错，服务端会返回该消息
        } else if (opcode.equals(WsOpCodeType.HELLO.getCode())) {
            // 当客户端与网关建立ws连接之后，网关下发的第一条消息，此时需要进行鉴权
            wsAuth();
        } else if (opcode.equals(WsOpCodeType.HEARTBEAT_ACK.getCode())) {
            // 当发送心跳成功之后，就会收到该消息
        }
    }


    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("WS连接已关闭");

        // 修改连接状态
        WsInit.CStatus = WsConnectStatus.DISCONNECT;
        // 初始化
        WsInit.last = null;

        log.info("重新连接。。。");
        initConnect();
    }

    @Override
    public void onError(Exception e) {
        log.info("error:", e);
    }

    /**
     * Ws auth.
     * 鉴权操作
     */
    public void wsAuth() {
        WsAuth auth = new WsAuth();
        auth.setOp(2);
        auth.setD(WsAuthD.build(MisakaConfiguration.token, 1073741826, null, null));
        String jsonStr = JSONObject.fromObject(auth).toString();
        send(jsonStr);
    }

    /**
     * Init connect.
     * 初始化Ws连接
     */
    public static void initConnect() {
        try {
            wsService = new MisakaWsService(new URI(BotApi.WS_URL));
            wsService.connect();
        } catch (URISyntaxException e) {
            log.info("URI Error:" + e.getMessage());
        }
    }
}
