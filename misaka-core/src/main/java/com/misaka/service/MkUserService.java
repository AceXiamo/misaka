package com.misaka.service;

import com.misaka.entity.MkUser;

import java.util.List;

/**
 * 频道用户表(MkUser)表服务接口
 *
 * @author xiamo
 * @since 2021-12-03 15:19:53
 */
public interface MkUserService {

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MkUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param mkUser 实例对象
     * @return 实例对象
     */
    MkUser insert(MkUser mkUser);

    /**
     * 新增数据，存在则修改
     *
     * @param mkUser 实例对象
     * @return 影响行数
     */
    void insertOrUpdate(MkUser mkUser);

    /**
     * 修改数据
     *
     * @param mkUser 实例对象
     * @return 实例对象
     */
    MkUser update(MkUser mkUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}