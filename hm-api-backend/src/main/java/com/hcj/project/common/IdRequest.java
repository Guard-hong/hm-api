package com.hcj.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:HCJ
 * @DateTime:2023/10/3
 * @Description:
 **/
@Data
public class IdRequest implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;
}
