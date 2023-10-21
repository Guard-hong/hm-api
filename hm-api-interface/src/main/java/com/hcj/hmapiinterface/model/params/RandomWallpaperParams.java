package com.hcj.hmapiinterface.model.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:HCJ
 * @DateTime:2023/10/20
 * @Description:
 **/
@Data
public class RandomWallpaperParams implements Serializable {
    private String lx;
    private String method;
}
