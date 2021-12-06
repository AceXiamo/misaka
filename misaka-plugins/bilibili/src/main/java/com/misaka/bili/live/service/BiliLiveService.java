package com.misaka.bili.live.service;

import com.misaka.bili.live.entity.BiliLive;

import java.util.List;

/**
 * B站直播表(BiliLive)表服务接口
 *
 * @author xiamo
 * @since 2021-12-03 16:24:35
 */
public interface BiliLiveService {

    /**
     * Add live string.
     * 通过房间号和昵称初始化数据
     *
     * @param roomId the room id
     * @param nick   the nick
     * @return the string
     */
    String addLive(String roomId, String nick);

    /**
     * Query by room id bili live.
     *
     * @param roomId the room id
     * @return the bili live
     */
    BiliLive queryByRoomId(Integer roomId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BiliLive queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<BiliLive> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param biliLive 实例对象
     * @return 实例对象
     */
    BiliLive insert(BiliLive biliLive);

    /**
     * 修改数据
     *
     * @param biliLive 实例对象
     * @return 实例对象
     */
    BiliLive update(BiliLive biliLive);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}