package com.misaka.enums;

import lombok.Getter;

/**
 * The type Weacher enums.
 *
 * @author xiamo
 * @Description:
 * @ClassName: WeacherEnums
 * @date 2021 /12/4 10:50
 */
@Getter
public enum WeacherEnums {

    /**
     * the weacher enums.
     */
//    CLOUD("☁️", "云"),
    PARTLY_SUNNY("⛅", "多云"),
    CLOUD_WITH_LIGHTNING_AND_RAIN("⛈️", "雷"),
    SUN_BEHIND_SMALL_CLOUD("\uD83C\uDF24️", "晴"),
    CLOUD_WITH_RAIN("\uD83C\uDF27️️", "雨"),
    cloud_with_snow("\uD83C\uDF28️️️", "雪"),
    ;

    WeacherEnums(String icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    /**
     * The Icon.
     */
    private String icon;
    /**
     * The Text.
     */
    private String text;

    public static String getIcon(String text) {
        String icon = "";
        for (WeacherEnums enums : WeacherEnums.values()) {
            if(enums.getText().contains(text)){
                icon = enums.getIcon();
                break;
            }
        }
        return icon;
    }

}
