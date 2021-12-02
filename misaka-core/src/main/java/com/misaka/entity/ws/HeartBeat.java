package com.misaka.entity.ws;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiamo
 * @Description:
 * @ClassName: HeartBeat
 * @date 2021/11/30 17:44
 */
@Data
@Builder
public class HeartBeat {
    private int op;
    private Integer d;

    public static HeartBeat build(int op, Integer d){
        return HeartBeat.builder()
                .op(op)
                .d(d)
                .build();
    }
}
