package com.misaka.bili.live.dao;

import com.misaka.bili.live.entity.BiliLive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * B站直播表(BiliLive)表数据库访问层
 *
 * @author xiamo
 * @since 2021-12-03 16:24:35
 */
@Mapper
public interface BiliLiveDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BiliLive queryById(Integer id);

    /**
     * Query by room id bili live.
     *
     * @param roomId the room id
     * @return the bili live
     */
    BiliLive queryByRoomId(Integer roomId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<BiliLive> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param biliLive 实例对象
     * @return 对象列表
     */
    List<BiliLive> queryAll(BiliLive biliLive);

    /**
     * 新增数据
     *
     * @param biliLive 实例对象
     * @return 影响行数
     */
    int insert(BiliLive biliLive);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BiliLive> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BiliLive> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BiliLive> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<BiliLive> entities);

    /**
     * 修改数据
     *
     * @param biliLive 实例对象
     * @return 影响行数
     */
    int update(BiliLive biliLive);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}