package com.misaka.entity.ws;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiamo
 * @Description:
 * @ClassName: WsAuthD
 * @date 2021/11/30 17:07
 */
@Data
@Builder
public class WsAuthD{
    private String token;
    private int intents;
    private int[] shard;
    private WsProperties properties;

    public static WsAuthD build(String token, int intents, int[] shard, WsProperties properties){
        return WsAuthD.builder()
                .token(token)
                .intents(intents)
                .shard(shard)
                .properties(properties)
                .build();
    }
}