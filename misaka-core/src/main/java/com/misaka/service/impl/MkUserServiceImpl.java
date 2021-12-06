package com.misaka.service.impl;

import com.misaka.entity.MkUser;
import com.misaka.dao.MkUserDao;
import com.misaka.service.MkUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 频道用户表(MkUser)表服务实现类
 *
 * @author xiamo
 * @since 2021-12-03 15:19:55
 */
@Service("mkUserService")
public class MkUserServiceImpl implements MkUserService {
    @Resource
    private MkUserDao mkUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MkUser queryById(Integer id) {
        return this.mkUserDao.queryById(id);
    }

    @Override
    public MkUser queryByUserId(String userId){
        return this.mkUserDao.queryByUserId(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MkUser> queryAllByLimit(int offset, int limit) {
        return this.mkUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param mkUser 实例对象
     * @return 实例对象
     */
    @Override
    public MkUser insert(MkUser mkUser) {
        this.mkUserDao.insert(mkUser);

        return mkUser;
    }
    /**
     * 新增数据
     *
     * @param mkUser 实例对象
     */
    @Override
    public void insertOrUpdate(MkUser mkUser) {
        this.mkUserDao.insertOrUpdate(mkUser);
    }

    /**
     * 修改数据
     *
     * @param mkUser 实例对象
     * @return 实例对象
     */
    @Override
    public MkUser update(MkUser mkUser) {
        this.mkUserDao.update(mkUser);
        return this.queryById(mkUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mkUserDao.deleteById(id) > 0;
    }
}