package com.misaka.service;

import com.misaka.entity.WeatherHis;

import java.util.List;

/**
 * 天气查询记录表(WeatherHis)表服务接口
 *
 * @author xiamo
 * @since 2021-12-04 10:04:18
 */
public interface WeatherHisService {

    /**
     * Weather string.
     *
     * @param city   the city
     * @param userId the user id
     * @return the string
     */
    String weather(String city, String userId, String msgId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeatherHis queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WeatherHis> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param weatherHis 实例对象
     * @return 实例对象
     */
    WeatherHis insert(WeatherHis weatherHis);

    /**
     * 修改数据
     *
     * @param weatherHis 实例对象
     * @return 实例对象
     */
    WeatherHis update(WeatherHis weatherHis);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}