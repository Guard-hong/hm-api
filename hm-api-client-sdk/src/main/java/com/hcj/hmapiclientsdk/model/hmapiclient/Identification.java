package com.hcj.hmapiclientsdk.model.hmapiclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identification {
    /**
     * 访问标识
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;
}
