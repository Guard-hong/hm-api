package com.hcj.hmapiclientsdk.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Data
public class UnifyResponse implements Serializable {
    private Map<String,Object> responseParams = new HashMap<>();

    @JsonAnyGetter
    public Map<String,Object> getResponseParams(){
        return responseParams;
    }
}
