package com.hcj.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoInfoRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 请求参数
     */
    private String requestParams;

   private static final long serialVersionUID = 1L;

}