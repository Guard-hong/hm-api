package com.hcj.hmapiclientsdk.model.response;

import lombok.Data;

import java.util.Date;

/**
 * @Author:HCJ
 * @DateTime:2023/10/20
 * @Description:
 **/
@Data
public class WeakerResponse extends UnifyResponse {
    /**
     * 城市
     */
    private String city;

    private Info info;

    @Data
    private class Info{
        private String date;

        private String week;

        private String type;

        private String low;

        private String high;

        private String fengxiang;

        private String fengli;

        private String tip;

        private Night night;

        private Air air;

        @Data
        private class Night{
            private String type;

            private String fengxiang;

            private String fengli;
        }

        @Data
        private class Air{
            private Integer aqi;

            private Integer aqi_level;

            private String aqi_name;

            private String co;

            private String no2;

            private String o3;

            private String pm10;

            private String so2;
        }
    }
}
