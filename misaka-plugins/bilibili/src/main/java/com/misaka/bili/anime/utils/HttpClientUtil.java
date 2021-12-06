package com.misaka.bili.anime.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * The type Http client util.
 */
@Slf4j
public class HttpClientUtil {

    /**
     * Do get string.
     *
     * @param url the url
     * @return the string
     */
    public static String doGet(String url) {
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(new URI(url));
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
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }
}
