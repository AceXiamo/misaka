package com.misaka.controller;

import com.misaka.entity.MkMessage;
import com.misaka.service.MkMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 频道消息表(MkMessage)表控制层
 *
 * @author xiamo
 * @since 2021-12-03 15:18:40
 */
@RestController
@RequestMapping("mkMessage")
public class MkMessageController {
    /**
     * 服务对象
     */
    @Resource
    private MkMessageService mkMessageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MkMessage selectOne(Integer id) {
        return this.mkMessageService.queryById(id);
    }

}