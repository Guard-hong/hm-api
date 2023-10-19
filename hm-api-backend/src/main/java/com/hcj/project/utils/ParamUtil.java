package com.hcj.project.utils;

import com.hcj.hmapiclientsdk.model.hmapiclient.Identification;
import com.hcj.hmapiclientsdk.model.request.UnifyRequest;

import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/19
 * @Description:
 **/
public class ParamUtil {

    public static Identification getIdentification(String accessKey,String secretKey){
        Identification identification = new Identification();
        identification.setAccessKey(accessKey);
        identification.setSecretKey(secretKey);
        return identification;
    }

    public static UnifyRequest getUnifyRequest(String url, String method, Map<String, Object> params){
        UnifyRequest unifyRequest = new UnifyRequest();
        unifyRequest.setPath(url);
        unifyRequest.setMethod(method);
        unifyRequest.setRequestParams(params);
        return unifyRequest;
    }
}
