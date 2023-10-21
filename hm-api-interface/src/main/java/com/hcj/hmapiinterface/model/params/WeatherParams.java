package com.hcj.hmapiinterface.model.params;

import lombok.Data;

/**
 * @Author:HCJ
 * @DateTime:2023/10/21
 * @Description:
 **/
@Data
public class WeatherParams {
    private String ip;
    private String city;
    private String type;
}
