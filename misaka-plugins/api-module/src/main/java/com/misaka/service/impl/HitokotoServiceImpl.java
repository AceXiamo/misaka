package com.misaka.service.impl;

import com.misaka.service.HitokotoService;
import com.misaka.utils.HttpClientUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author xiamo
 * @Description:
 * @ClassName: HitokotoServiceImpl
 * @date 2021/12/4 14:41
 */
@Service
public class HitokotoServiceImpl implements HitokotoService {

    /**
     * The constant HITOKOTO_API.
     * 固定返回和动漫相关的句子
     */
    private static final String HITOKOTO_API = "https://v1.hitokoto.cn/?c=a";

    @Override
    public String queryHitokoto(){
        StringBuffer msg = new StringBuffer();
        String res = HttpClientUtil.doGet(HITOKOTO_API);
        JSONObject json = JSONObject.fromObject(res);
        msg.append("---------\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF1F---------").append("\n");
        msg.append("“").append(json.get("hitokoto").toString()).append("” ");
        msg.append("- 《").append(json.get("from").toString()).append("》\n");
        msg.append("---------\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF1F---------");
        return msg.toString();
    }

}
