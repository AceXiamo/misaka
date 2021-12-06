package com.misaka.dao;

import com.misaka.entity.MkMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 频道消息表(MkMessage)表数据库访问层
 *
 * @author xiamo
 * @since 2021-12-03 15:18:39
 */
@Mapper
public interface MkMessageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MkMessage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MkMessage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param mkMessage 实例对象
     * @return 对象列表
     */
    List<MkMessage> queryAll(MkMessage mkMessage);

    /**
     * 新增数据
     *
     * @param mkMessage 实例对象
     * @return 影响行数
     */
    int insert(MkMessage mkMessage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MkMessage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MkMessage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MkMessage> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<MkMessage> entities);

    /**
     * 修改数据
     *
     * @param mkMessage 实例对象
     * @return 影响行数
     */
    int update(MkMessage mkMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}