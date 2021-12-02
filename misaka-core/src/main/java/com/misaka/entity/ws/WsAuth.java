package com.misaka.entity.ws;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiamo
 * @Description:
 * @ClassName: WsVo
 * @date 2021/11/30 16:55
 */
@Data
public class WsAuth {
    private Integer op;
    private WsAuthD d;
}