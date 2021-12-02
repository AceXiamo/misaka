package com.bili.anime.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
