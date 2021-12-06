package com.misaka.dao;

import com.misaka.entity.MkUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 频道用户表(MkUser)表数据库访问层
 *
 * @author xiamo
 * @since 2021 -12-03 15:19:52
 */
@Mapper
public interface MkUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MkUser queryById(Integer id);

    /**
     * Query by user id mk user.
     *
     * @param userId the user id
     * @return the mk user
     */
    MkUser queryByUserId(String userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MkUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param mkUser 实例对象
     * @return 对象列表
     */
    List<MkUser> queryAll(MkUser mkUser);

    /**
     * 新增数据
     *
     * @param mkUser 实例对象
     * @return 影响行数
     */
    int insert(MkUser mkUser);

    /**
     * 新增数据，存在则修改
     *
     * @param mkUser 实例对象
     * @return 影响行数
     */
    int insertOrUpdate(MkUser mkUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MkUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MkUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MkUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<MkUser> entities);

    /**
     * 修改数据
     *
     * @param mkUser 实例对象
     * @return 影响行数
     */
    int update(MkUser mkUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}