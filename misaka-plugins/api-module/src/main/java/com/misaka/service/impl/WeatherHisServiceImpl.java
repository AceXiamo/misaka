package com.misaka.service.impl;

import com.misaka.dao.WeatherHisDao;
import com.misaka.entity.WeatherHis;
import com.misaka.enums.WeacherEnums;
import com.misaka.service.WeatherHisService;
import com.misaka.utils.HttpClientUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 天气查询记录表(WeatherHis)表服务实现类
 *
 * @author xiamo
 * @since 2021-12-04 10:04:18
 */
@Service("weatherHisService")
public class WeatherHisServiceImpl implements WeatherHisService {

    @Resource
    private WeatherHisDao weatherHisDao;

    private static final String CITY_API = "https://wis.qq.com/city/like?source=pc&city=";

    private static final String WEATHER_API = "https://wis.qq.com/weather/common?source=pc&weather_type=observe";

    @Override
    public String weather(String city, String userId, String msgId) {
        StringBuffer msg = new StringBuffer();
        String[] cityData = getCity(city);
        String province = cityData[0];
        String cityNew = cityData[1];
        String res = HttpClientUtil.doGet(WEATHER_API + "&province=" + province + "&city=" + cityNew);
        JSONObject json = JSONObject.fromObject(res);
        JSONObject data = JSONObject.fromObject(json.get("data"));
        // 拿到的天气信息
        JSONObject observe = JSONObject.fromObject(data.get("observe"));

        msg.append("------").append(cityNew).append("当前天气").append("------\n");
        msg.append(WeacherEnums.getIcon(observe.get("weather").toString())).append("️ ").append(observe.get("weather").toString()).append("\n");
        msg.append("\uD83C\uDF21️ ").append("温度：").append(observe.get("degree").toString()).append("℃").append("\n");
        msg.append("\uD83D\uDCA7️ ").append("湿度：").append(observe.get("humidity").toString()).append("%").append("\n");
        msg.append("⚡️ ").append("气压：").append(observe.get("pressure").toString()).append("hPa").append("\n");
        msg.append("\uD83C\uDF0A ").append("降水量：").append(observe.get("precipitation").toString()).append("mm").append("\n");
        msg.append("--------------------------");
        WeatherHis his = new WeatherHis();
        his.setId(UUID.randomUUID().toString());
        his.setCity(cityNew);
        his.setUserId(userId);
        his.setMsgId(msgId);
        insert(his);
        return msg.toString();
    }

    /**
     * Get city string [ ].
     *
     * @param city the city
     * @return the string [ ]
     */
    private String[] getCity(String city) {
        String res = HttpClientUtil.doGet(CITY_API + city);
        JSONObject json = JSONObject.fromObject(res);
        JSONObject data = JSONObject.fromObject(json.get("data"));
        String[] cityData = new String[]{};
        // 默认取第一个
        for (Object key : data.keySet()) {
            String cityStr = data.get(key.toString()).toString();
            cityStr = cityStr.replaceAll(" ", "");
            cityData = cityStr.split(",");
            break;
        }
        return cityData;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WeatherHis queryById(String id) {
        return this.weatherHisDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WeatherHis> queryAllByLimit(int offset, int limit) {
        return this.weatherHisDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weatherHis 实例对象
     * @return 实例对象
     */
    @Override
    public WeatherHis insert(WeatherHis weatherHis) {
        this.weatherHisDao.insert(weatherHis);
        return weatherHis;
    }

    /**
     * 修改数据
     *
     * @param weatherHis 实例对象
     * @return 实例对象
     */
    @Override
    public WeatherHis update(WeatherHis weatherHis) {
        this.weatherHisDao.update(weatherHis);
        return this.queryById(weatherHis.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.weatherHisDao.deleteById(id) > 0;
    }
}