package com.hcj.hmapiclientsdk.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应类
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Data
public class UnifyResponse implements Serializable {
    private Map<String, Object> data = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    @JsonAnySetter
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
