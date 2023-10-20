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
     * 毒鸡汤
     * @param identification
     * @param unifyRequest
     * @return
     */
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(Identification identification,UnifyRequest unifyRequest){
        PoisonousChickenSoupResponse response = request(identification, unifyRequest, PoisonousChickenSoupResponse.class);
        return response;
    }

    /**
     * 随机壁纸
     * @param identification
     * @param unifyRequest
     * @return
     */
    public ImageResponse getRandomWallpaper(Identification identification, UnifyRequest unifyRequest){
        ImageResponse response = request(identification, unifyRequest, ImageResponse.class);
        return response;
    }

    /**
     * 获取指定qq的头像
     * @param identification
     * @param unifyRequest
     * @return
     */
    public ImageResponse getQQAvatar(Identification identification,UnifyRequest unifyRequest){
        // 参数校验
        if(unifyRequest.getRequestParams().get(QQ_AVATAR_QQ) == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ImageResponse response = request(identification, unifyRequest, ImageResponse.class);
        return response;
    }

    /**
     * 获取天气
     * @param identification
     * @param unifyRequest
     * @return
     */
    public String getWeather(Identification identification, UnifyRequest unifyRequest){
        String res = doResponse(identification, unifyRequest);
        return res;
    }
}
