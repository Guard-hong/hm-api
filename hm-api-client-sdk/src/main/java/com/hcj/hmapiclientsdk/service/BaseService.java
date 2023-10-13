package com.hcj.hmapiclientsdk.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.hcj.hmapiclientsdk.client.HmApiClient;
import com.hcj.hmapiclientsdk.common.ErrorCode;
import com.hcj.hmapiclientsdk.constant.GatewayHostConstant;
import com.hcj.hmapiclientsdk.exception.BusinessException;
import com.hcj.hmapiclientsdk.model.enums.MethodEnum;
import com.hcj.hmapiclientsdk.model.request.UnifyRequest;
import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import com.hcj.hmapiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
@Slf4j
public abstract class BaseService {

    @Resource
    private HmApiClient hmApiClient;
    /**
     * 封装请求头
     * @param body
     * @param hmApiClient
     * @return
     */
    public Map<String, String> getHeaders(String body, HmApiClient hmApiClient){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        String encodedBody = SecureUtil.md5(body);
        headers.put("body",encodedBody);
        headers.put("accessKey",hmApiClient.getAccessKey());
        headers.put("sign", SignUtils.genSign(encodedBody,hmApiClient.getSecretKey()));
        // todo 增加请求头，增强完整性和安全性
        return headers;
    }

    /**
     * 发送请求
     * @param unifyRequest
     * @return
     */
    public HttpResponse doRequest(UnifyRequest unifyRequest){
        String gatewayHost = GatewayHostConstant.GATEWAY_HOST;
        String method = unifyRequest.getMethod().trim().toUpperCase();
        String path = unifyRequest.getPath().trim(); // path: /api/xx/*
        Map<String, Object> requestParams = unifyRequest.getRequestParams();
        path = gatewayHost + path;
        if(! MethodEnum.getValues().contains(method)){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"请求方法不存在");
        }
        if(StringUtils.isBlank(path)){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"请求路径不存在");
        }
        HttpRequest httpRequest = null;
        switch (method){
            case "GET" :{
                // 拼接get参数 /api/xx?username=cc&password=xx
                path = splicingGetRequest(path,requestParams);
                httpRequest = HttpRequest.get(path);
                break;
            }
            case "POST" :{
                httpRequest = HttpRequest.post(path);
                break;
            }
            default :{
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"请求方法不存在");
            }
        }
        log.info("{}请求路径是： {}",method,path);
        return httpRequest.
                addHeaders(getHeaders(JSONUtil.toJsonStr(unifyRequest),hmApiClient)).
                body(JSONUtil.toJsonStr(unifyRequest.getRequestParams())).execute();
    }

    /**
     * 响应
     * @param unifyRequest
     * @return
     */
    public UnifyResponse doResponse(UnifyRequest unifyRequest){

        return null;
    }

    /**
     * 给get请求添加请求参数
     * @param path
     * @param requestParams
     * @return
     */
    private String splicingGetRequest(String path,Map<String,Object> requestParams){
        StringBuilder sb = new StringBuilder(path);
        if(!requestParams.isEmpty()){
            sb.append("?");
            for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
