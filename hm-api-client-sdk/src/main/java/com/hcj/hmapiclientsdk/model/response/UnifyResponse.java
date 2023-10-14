package com.hcj.hmapiclientsdk.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
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
    /**
     * 响应数据（json）
     */
    private String data;

}
