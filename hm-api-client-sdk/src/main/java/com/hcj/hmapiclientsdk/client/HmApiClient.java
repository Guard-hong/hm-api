package com.hcj.hmapiclientsdk.client;

import com.hcj.hmapiclientsdk.common.ErrorCode;
import com.hcj.hmapiclientsdk.exception.BusinessException;
import com.hcj.hmapiclientsdk.model.hmapiclient.Identification;
import com.hcj.hmapiclientsdk.model.request.UnifyRequest;
import com.hcj.hmapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hcj.hmapiclientsdk.model.response.ImageResponse;

import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import com.hcj.hmapiclientsdk.model.response.WeakerResponse;
import lombok.extern.slf4j.Slf4j;

import static com.hcj.hmapiclientsdk.constant.ValidateParamConstant.QQ_AVATAR_QQ;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 **/
@Slf4j
public class HmApiClient extends BaseClient {

    /**
     * 统一请求
     * @param identification
     * @param unifyRequest
     * @return
     */
    public String doUnifyRequest(Identification identification,UnifyRequest unifyRequest){
        return request(identification,unifyRequest);
    }
}
