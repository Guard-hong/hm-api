package com.hcj.hmapiinterface.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Data
@AllArgsConstructor
public class Test implements Serializable {
    private String name;
    private String password;
    private String email;
}
