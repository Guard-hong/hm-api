package com.hcj.hmapiinterface.controller;

import cn.hutool.json.JSONUtil;
import com.hcj.hmapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hcj.hmapiclientsdk.model.response.ImageResponse;
import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import com.hcj.hmapiinterface.model.QQAvatarParams;
import com.hcj.hmapiinterface.model.RandomWallpaperParams;
import com.hcj.hmapiinterface.model.WeatherParams;
import com.hcj.hmapiinterface.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hcj.hmapiinterface.utils.RequestUtils.buildUrl;
import static com.hcj.hmapiinterface.utils.RequestUtils.get;

/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping("/poisonousChickenSoup")
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(){
        String url = "https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json";
        String res = get(url);
        PoisonousChickenSoupResponse poisonousChickenSoupResponse =
                JSONUtil.toBean(res, PoisonousChickenSoupResponse.class);
        return poisonousChickenSoupResponse;
    }

    @GetMapping("/randomWallpaper")
    public ImageResponse getRandomWallpaper(RandomWallpaperParams randomWallpaperParams) throws Exception {
        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
        String url = buildUrl(baseUrl, randomWallpaperParams);
        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        String res = get(url);
        return JSONUtil.toBean(res, ImageResponse.class);
    }

    @GetMapping("/QQAvatar")
    public ImageResponse getQQAvatar(QQAvatarParams qqAvatarParams) throws Exception {
        String baseUrl = "https://api.vvhan.com/api/qq";
        String url = buildUrl(baseUrl, qqAvatarParams);
        String res = get(url);
        return JSONUtil.toBean(res, ImageResponse.class);
    }

    @GetMapping("/weather")
    public UnifyResponse getWeaker(WeatherParams weatherParams) {
        String baseUrl = "https://api.vvhan.com/api/weather";
        UnifyResponse response = ResponseUtils.baseResponse(baseUrl, weatherParams);
        return response;
    }
}
