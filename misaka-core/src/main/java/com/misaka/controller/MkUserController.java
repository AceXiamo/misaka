package com.misaka.controller;

import com.misaka.entity.MkUser;
import com.misaka.service.MkUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 频道用户表(MkUser)表控制层
 *
 * @author xiamo
 * @since 2021-12-03 15:19:56
 */
@RestController
@RequestMapping("mkUser")
public class MkUserController {
    /**
     * 服务对象
     */
    @Resource
    private MkUserService mkUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MkUser selectOne(Integer id) {
        return this.mkUserService.queryById(id);
    }

}