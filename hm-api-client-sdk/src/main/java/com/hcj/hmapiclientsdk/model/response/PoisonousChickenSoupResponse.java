package com.hcj.hmapiclientsdk.model.response;

import lombok.Data;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@Data
public class PoisonousChickenSoupResponse extends UnifyResponse  {
    /**
     * 毒鸡汤文本
     */
    private String text;
}
