package com.misaka.utils;

import com.misaka.config.MisakaConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author xiamo
 * @Description:
 * @ClassName: BotHttpUtils
 * @date 2021/11/30 16:24
 */
@Slf4j
public class MisakaHttpUtils {

    /**
     * Get string.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String get(String url, Map<String, String> params) {
        // 重新拼接Url
        for (String key : params.keySet()) {
            if (url.contains(key)) {
                url = url.replace(key, params.get(key));
                params.remove(key);
            }
        }
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Authorization", MisakaConfiguration.token);
            HttpResponse response = httpClient.execute(httpGet);
            response.setHeader("Authorization", MisakaConfiguration.token);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                log.info("请求失败！");
            }
        } catch (Exception e) {
            log.info("请求失败!");
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            // 释放连接
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    /**
     * Get string.
     *
     * @param url the url
     * @return the string
     */
    public static String get(String url) {
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(new URI(url));
            httpGet.setHeader("Authorization", MisakaConfiguration.token);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                log.info("请求失败！");
            }
        } catch (Exception e) {
            log.info("请求失败!");
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            // 释放连接
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }


    /**
     * Send json str string.
     *
     * @param url    the url
     * @param params the params
     * @return the string
     */
    public static String sendJsonStr(String url, Map<String, String> params, String json) {
        // 重新拼接Url
        for (String key : params.keySet()) {
            if (url.contains(key)) {
                url = url.replace(key, params.get(key));
                params.remove(key);
            }
        }
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Authorization", MisakaConfiguration.token);
            if (StringUtils.isNotBlank(json)) {
                httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                log.info("请求失败");
            }
        } catch (IOException e) {
            log.error("请求异常");
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

}
