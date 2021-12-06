package com.misaka.bili.anime.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xiamo
 * @Description: B站连载番剧返回
 * @ClassName: AnimeData
 * @date 2021/12/2 14:49
 */
@Data
public class AnimeData {
    /**
     * The Date.
     * 时间
     */
    private String date;
    /**
     * The Date ts.
     * 时间戳
     */
    private Integer date_ts;
    /**
     * The Day of week.
     * 1：周一   2：周二  3：周三  4：周四  5：周五  6：周六  7：周日
     */
    private Integer day_of_week;
    /**
     * The Episodes.
     * 更新内容
     */
    private List<AnimeEpisodes> episodes;
}
