package com.misaka.entity;

import lombok.Builder;
import lombok.Data;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * The type Msg handle.
 *
 * @author xiamo
 * @Description:
 * @ClassName: MsgHandle
 * @date 2021 /12/2 15:51
 */
@Data
@Builder
public class MsgHandle {

    /**
     * The S.
     */
    public String s;

    /**
     * The Status.
     * 0 未处理，  1 已处理
     */
    public String status;

    /**
     * Build msg handle.
     *
     * @param obj    the obj
     * @param status the status
     * @return the msg handle
     */
    public static MsgHandle build(String s, String status) {
        return MsgHandle.builder().s(s).status(status).build();
    }

}
