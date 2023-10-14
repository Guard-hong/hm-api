package com.hcj.hmapiclientsdk.client;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.hcj.hmapiclientsdk.common.ErrorCode;
import com.hcj.hmapiclientsdk.constant.GatewayHostConstant;
import com.hcj.hmapiclientsdk.exception.BusinessException;
import com.hcj.hmapiclientsdk.model.enums.MethodEnum;
import com.hcj.hmapiclientsdk.model.hmapiclient.Identification;
import com.hcj.hmapiclientsdk.model.request.UnifyRequest;
import com.hcj.hmapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import com.hcj.hmapiclientsdk.utils.SignUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 **/
@Slf4j
public class HmApiClient extends BaseClient {

    public PoisonousChickenSoupResponse getPoisonousChickenSoup(Identification identification,UnifyRequest unifyRequest){
        PoisonousChickenSoupResponse response = request(identification, unifyRequest, PoisonousChickenSoupResponse.class);
        return response;
    }

}
