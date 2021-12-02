package com.bili.anime.entity;

import lombok.Data;

/**
 * @author xiamo
 * @Description:
 * @ClassName: AnimeEpisodes
 * @date 2021/12/2 14:51
 */
@Data
public class AnimeEpisodes {
    /**
     * The Cover.
     * 番剧封面图
     */
    private String cover;
    /**
     * The Delay.
     */
    private Integer delay;
    /**
     * The Delay id.
     */
    private Integer delay_id;
    /**
     * The Episode id.
     */
    private Integer episode_id;
    /**
     * The Pub ts.
     */
    private Integer pub_ts;
    /**
     * The Published.
     */
    private Integer published;
    /**
     * The Season id.
     */
    private Integer season_id;
    /**
     * The Delay index.
     */
    private String delay_index;
    /**
     * The Delay reason.
     */
    private String delay_reason;
    /**
     * The Ep cover.
     */
    private String ep_cover;
    /**
     * The Follows.
     */
    private String follows;
    /**
     * The Plays.
     */
    private String plays;
    /**
     * The Pub index.
     * 更新至多少话， 例：第10话
     */
    private String pub_index;
    /**
     * The Pub time.
     * 更新时间，  例：22:00
     */
    private String pub_time;
    /**
     * The Square cover.
     */
    private String square_cover;
    /**
     * The Title.
     * 番剧名
     */
    private String title;
}
