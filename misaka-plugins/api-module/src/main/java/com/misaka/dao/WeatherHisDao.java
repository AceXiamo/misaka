package com.misaka.dao;

import com.misaka.entity.WeatherHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 天气查询记录表(WeatherHis)表数据库访问层
 *
 * @author xiamo
 * @since 2021-12-04 10:04:18
 */
@Mapper
public interface WeatherHisDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeatherHis queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WeatherHis> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param weatherHis 实例对象
     * @return 对象列表
     */
    List<WeatherHis> queryAll(WeatherHis weatherHis);

    /**
     * 新增数据
     *
     * @param weatherHis 实例对象
     * @return 影响行数
     */
    int insert(WeatherHis weatherHis);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeatherHis> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WeatherHis> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeatherHis> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<WeatherHis> entities);

    /**
     * 修改数据
     *
     * @param weatherHis 实例对象
     * @return 影响行数
     */
    int update(WeatherHis weatherHis);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}