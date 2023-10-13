package com.hcj.hmapiclientsdk.model.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
@Data
public class UnifyRequest implements Serializable {
    /**
     * 请求参数
     */
    private Map<String,Object> requestParams = new HashMap<>();

    /**
     * 请求路径
     * 例如： /api/user
     */
    private String path;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 设置请求参数
     */
    @JsonAnySetter
    public void setRequestParams(String key,Object value){
        this.requestParams.put(key,value);
    }

    /**
     * 获取请求参数
     */
    @JsonAnyGetter
    public Map<String,Object> getRequestParams(){
        return requestParams;
    }
}
