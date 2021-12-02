package com.misaka.utils2;

import javax.xml.bind.DatatypeConverter;

/**
 * @author xiamo
 * @Description: 视频转格式工具类
 * @ClassName: VideoUtils
 * @date 2021/6/7 14:50
 */
public class VideoUtils {


    /**
     * byte[] 转  base64
     * @param file
     * @return base64
     */
    public static String videoToBase64(byte[] file) {
        return DatatypeConverter.printBase64Binary(file);
    }
}
