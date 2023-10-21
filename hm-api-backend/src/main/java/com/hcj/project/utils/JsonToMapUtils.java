package com.hcj.project.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/21
 * @Description:
 **/
public class JsonToMapUtils {

    private static final Gson gson = new Gson();

    public static Map<String, Object> stringToMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}
