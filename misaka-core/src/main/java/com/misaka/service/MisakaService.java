package com.misaka.service;

import com.bili.anime.entity.AnimeData;
import com.bili.anime.service.AnimeTimeLineService;
import com.misaka.config.BotApi;
import com.misaka.config.MisakaConfiguration;
import com.misaka.utils.MisakaHttpUtils;
import com.misaka.utils.MqUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Misaka service.
 *
 * @author xiamo
 * @Description:
 * @ClassName: MisakaService
 * @date 2021 /11/30 15:29
 */
@Slf4j
@Component
public class MisakaService {

    public void serverInit() {
        // init token
        MisakaConfiguration.instance.setToken();
        log.info("御坂启动中。。。");
        log.info("连接WS中。。。");
        String res = MisakaHttpUtils.get(BotApi.WS);
        JSONObject jsonObject = JSONObject.fromObject(res);
        BotApi.WS_URL = (String) jsonObject.get("url");
        log.info("WS地址为:" + BotApi.WS_URL);
        MisakaWsService.initConnect();
    }
}
