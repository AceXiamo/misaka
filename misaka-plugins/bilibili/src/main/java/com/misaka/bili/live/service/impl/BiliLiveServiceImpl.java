package com.misaka.bili.live.service.impl;

import com.misaka.bili.anime.utils.HttpClientUtil;
import com.misaka.bili.live.dao.BiliLiveDao;
import com.misaka.bili.live.entity.BiliLive;
import com.misaka.bili.live.entity.BiliLiveUser;
import com.misaka.bili.live.service.BiliLiveService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONFunction;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * B站直播表(BiliLive)表服务实现类
 *
 * @author xiamo
 * @since 2021-12-03 16:24:35
 */
@Slf4j
@Service("biliLiveService")
public class BiliLiveServiceImpl implements BiliLiveService {

    @Resource
    private BiliLiveDao biliLiveDao;

    private static final String LIE_API = "https://api.live.bilibili.com/room/v1/Room/get_info_by_id?ids[]=";

    @Override
    public String addLive(String roomId, String nick) {
        String url = LIE_API + roomId;
        String res = HttpClientUtil.doGet(url);
        JSONObject json = JSONObject.fromObject(res);
        String data = json.get("data").toString();
        if (data.equals("[]")) {
            // 判空
            return "\uD83D\uDD34 没有搜索到哟，御坂御坂如是说道。";
        }
        BiliLiveUser live = null;
        JSONObject dataJson = JSONObject.fromObject(data);
        for (Object key : dataJson.keySet()) {
            JSONObject keyData = JSONObject.fromObject(dataJson.get(key.toString()));
            live = (BiliLiveUser) JSONObject.toBean(keyData, BiliLiveUser.class);
        }
        BiliLive biliLive = queryByRoomId(Integer.parseInt(live.getRoomid()));
        if (biliLive == null) {
            biliLive = new BiliLive();
            biliLive.setBiliLiveRoomId(Integer.parseInt(live.getRoomid()));
            if (StringUtils.isEmpty(nick)) {
                biliLive.setNick(live.getUname());
            } else {
                biliLive.setNick(live.getUname() + "," + nick);
            }
            insert(biliLive);
        } else {
            return "\uD83D\uDFE2 已经添加过啦，御坂御坂如是说道。";
        }
        return "\uD83D\uDFE2 添加成功！御坂御坂肯定的说道。";
    }

    @Override
    public BiliLive queryByRoomId(Integer roomId){
        return this.biliLiveDao.queryByRoomId(roomId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BiliLive queryById(Integer id) {
        return this.biliLiveDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<BiliLive> queryAllByLimit(int offset, int limit) {
        return this.biliLiveDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param biliLive 实例对象
     * @return 实例对象
     */
    @Override
    public BiliLive insert(BiliLive biliLive) {
        this.biliLiveDao.insert(biliLive);
        return biliLive;
    }

    /**
     * 修改数据
     *
     * @param biliLive 实例对象
     * @return 实例对象
     */
    @Override
    public BiliLive update(BiliLive biliLive) {
        this.biliLiveDao.update(biliLive);
        return this.queryById(biliLive.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.biliLiveDao.deleteById(id) > 0;
    }
}