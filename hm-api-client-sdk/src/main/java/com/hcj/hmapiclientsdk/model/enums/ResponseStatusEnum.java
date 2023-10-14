package com.hcj.hmapiclientsdk.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
public enum ResponseStatusEnum {
    SUCCESS("success",200);

    private final String text;

    private final Integer value;

    ResponseStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
