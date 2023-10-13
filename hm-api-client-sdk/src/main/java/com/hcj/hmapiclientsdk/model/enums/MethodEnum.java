package com.hcj.hmapiclientsdk.model.enums;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
public enum MethodEnum {
    GET("GET","GET"),
    POST("POST","POST");

    private final String text;

    private final String value;

    MethodEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
