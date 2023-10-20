package com.hcj.hmapiinterface.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hcj.hmapiclientsdk.common.ErrorCode;
import com.hcj.hmapiclientsdk.exception.BusinessException;
import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import static com.hcj.hmapiinterface.utils.RequestUtils.get;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/21
 * @Description:
 **/
public class ResponseUtils {
    public static Map<String, Object> responseToMap(String response) {
        return new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static <T> UnifyResponse baseResponse(String baseUrl, T params) {
        String response = null;
        try {
            response = get(baseUrl, params);
            Map<String, Object> fromResponse = responseToMap(response);
            boolean success = (boolean) fromResponse.get("success");
            UnifyResponse baseResponse = new UnifyResponse();
            if (!success) {
                baseResponse.setData(fromResponse);
                return baseResponse;
            }
            fromResponse.remove("success");
            baseResponse.setData(fromResponse);
            return baseResponse;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "构建url异常");
        }
    }
}
