package com.misaka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiamo
 * @Description:
 * @ClassName: MisakaConfig
 * @date 2021/11/30 14:47
 */
@Slf4j
@Configuration
public class MisakaConfiguration {

    public static MisakaConfiguration instance = new MisakaConfiguration();

    public static String botId;
    public static String botToken;
    public static String botSecret;
    public static String token;

    @Value("${misaka.bot-id}")
    public void setBotId(String botId) {
        MisakaConfiguration.botId = botId;
    }

    @Value("${misaka.bot-token}")
    public void setBotToken(String botToken) {
        MisakaConfiguration.botToken = botToken;
    }

    @Value("${misaka.bot-secret}")
    public void setBotSecret(String botSecret) {
        MisakaConfiguration.botSecret = botSecret;
    }

    public void setToken() {
        MisakaConfiguration.token = "Bot " + MisakaConfiguration.botId + "." + MisakaConfiguration.botToken;
    }

}
