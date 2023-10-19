package com.hcj.project.model.dto.interfaceinfo;

import lombok.Data;

/**
 * @Author: hcj
 * @Date: 2023/10/15 03:52:36
 * @Version: 1.0
 * @Description: 请求参数字段
 */
@Data
public class RequestParamsField {
    private String id;
    private String fieldName;
    private String type;
    private String desc;
    private String required;
}