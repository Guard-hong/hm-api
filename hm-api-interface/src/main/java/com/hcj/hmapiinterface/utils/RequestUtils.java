package com.hcj.hmapiinterface.utils;

import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Slf4j
public class RequestUtils {

    /**
     * get请求
     *
     * @param url url
     * @return {@link String}
     */
    public static String get(String url){
        String body = HttpRequest.get(url).execute().body();
        log.info("【interface】：请求地址：{}，响应数据：{}", url, body);
        return body;
    }
}
