package com.hcj.hmapiinterface.utils;

import cn.hutool.http.HttpRequest;
import com.hcj.hmapiclientsdk.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Slf4j
public class RequestUtils {
    /**
     * 生成url
     *
     * @param baseUrl 基本url
     * @param params  params
     * @return {@link String}
     * @throws Exception api异常
     */
    public static <T> String buildUrl(String baseUrl, T params) throws Exception {
        StringBuilder url = new StringBuilder(baseUrl);
        Field[] fields = params.getClass().getDeclaredFields();
        boolean isFirstParam = true;
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(params);
                if (value != null) {
                    if (isFirstParam) {
                        url.append("?").append(name).append("=").append(value);
                        isFirstParam = false;
                    } else {
                        url.append("&").append(name).append("=").append(value);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new Exception("构建url异常");
            }
        }
        return url.toString();
    }

    /**
     * get请求
     *
     * @param baseUrl 基本url
     * @param params  params
     * @return {@link String}
     * @throws Exception api异常
     */
    public static <T> String get(String baseUrl, T params) throws Exception {
        return get(buildUrl(baseUrl, params));
    }
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
