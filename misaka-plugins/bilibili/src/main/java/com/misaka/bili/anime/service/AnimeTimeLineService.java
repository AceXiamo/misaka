package com.misaka.bili.anime.service;

import com.misaka.bili.anime.entity.AnimeData;
import com.misaka.bili.anime.entity.AnimeEpisodes;
import com.misaka.bili.anime.utils.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiamo
 * @Description:
 * @ClassName: AnimeTimeLineServiceImpl
 * @date 2021/12/2 19:33
 */
public class AnimeTimeLineService {

    /**
     * The constant ANIME_TIMELINE_API.
     * 返回本周更新
     * &before=6&after=6
     */
    private final static String ANIME_TIMELINE_API = "https://api.bilibili.com/pgc/web/timeline?types=1";

    public static String selectAnimeTimeLine() {
        StringBuilder msg = new StringBuilder();
        String jsonStr = HttpClientUtil.doGet(ANIME_TIMELINE_API);
        JSONObject json = JSONObject.fromObject(jsonStr);
        JSONArray res = JSONArray.fromObject(json.get("result"));

        List<AnimeData> list = Arrays.asList((AnimeData[]) JSONArray.toArray(res, AnimeData.class));
        for (AnimeData data : list) {
            JSONArray arr = JSONArray.fromObject(data.getEpisodes());
            data.setEpisodes(Arrays.asList((AnimeEpisodes[]) JSONArray.toArray(arr, AnimeEpisodes.class)));
        }
        for (AnimeData r1 : list) {
            StringBuilder day = new StringBuilder("\uD83C\uDF6D " + r1.getDate() + "\n");
            for (AnimeEpisodes r2 : r1.getEpisodes()) {
                day.append(r2.getTitle()).append("   ").append(r2.getPub_index()).append("\n");
            }
            msg.append("\n").append(day);
        }
        return msg.toString();
    }

}
