package com.misaka.service.impl;

import com.misaka.dao.MkMessageDao;
import com.misaka.entity.MkMessage;
import com.misaka.service.MkMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 频道消息表(MkMessage)表服务实现类
 *
 * @author xiamo
 * @since 2021-12-03 15:18:40
 */
@Service("mkMessageService")
public class MkMessageServiceImpl implements MkMessageService {
    @Resource
    private MkMessageDao mkMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MkMessage queryById(Integer id) {
        return this.mkMessageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MkMessage> queryAllByLimit(int offset, int limit) {
        return this.mkMessageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param mkMessage 实例对象
     * @return 实例对象
     */
    @Override
    public MkMessage insert(MkMessage mkMessage) {
        this.mkMessageDao.insert(mkMessage);
        return mkMessage;
    }

    /**
     * 修改数据
     *
     * @param mkMessage 实例对象
     * @return 实例对象
     */
    @Override
    public MkMessage update(MkMessage mkMessage) {
        this.mkMessageDao.update(mkMessage);
        return this.queryById(mkMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.mkMessageDao.deleteById(id) > 0;
    }
}