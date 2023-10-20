package com.hcj.hmapiclientsdk.model.response;

import lombok.Data;

/**
 * @Author:HCJ
 * @DateTime:2023/10/20
 * @Description:
 **/
@Data
public class ImageResponse extends UnifyResponse {

    /**
     * 返回图片地址
     */
    private String imgurl;
}
