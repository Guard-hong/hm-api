package com.hcj.hmapiinterface.controller;

import com.hcj.hmapiclientsdk.model.response.UnifyResponse;
import com.hcj.hmapiinterface.model.params.*;
import com.hcj.hmapiinterface.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author:HCJ
 * @DateTime:2023/10/14
 * @Description:
 **/
@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping("/poisonousChickenSoup")
    public UnifyResponse getPoisonousChickenSoup(){
        String baseUrl = "https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json";
        return RequestUtils.baseRequest(baseUrl);
    }

    @GetMapping("/randomWallpaper")
    public UnifyResponse getRandomWallpaper(RandomWallpaperParams randomWallpaperParams) throws Exception {
        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
        String url = RequestUtils.buildUrl(baseUrl, randomWallpaperParams);
        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        return RequestUtils.baseRequest(url);
    }

    @GetMapping("/QQAvatar")
    public UnifyResponse getQQAvatar(QQAvatarParams qqAvatarParams) throws Exception {
        String baseUrl = "https://api.vvhan.com/api/qq";
        return RequestUtils.baseRequest(baseUrl,qqAvatarParams);
    }

    @GetMapping("/weather")
    public UnifyResponse getWeaker(WeatherParams weatherParams) {
        String baseUrl = "https://api.vvhan.com/api/weather";
        return RequestUtils.baseRequest(baseUrl, weatherParams);
    }

    @GetMapping("/motivationalEnglish")
    public UnifyResponse getMotivationalEnglish(MotivationalEnglishParams motivationalEnglishParams) {
        String baseUrl = "https://api.vvhan.com/api/en";
        return RequestUtils.baseRequest(baseUrl, motivationalEnglishParams);
    }

    @GetMapping("/wbhot")
    public UnifyResponse getWbhot() {
        String baseUrl = "https://api.vvhan.com/api/wbhot";
        return RequestUtils.baseRequest(baseUrl);
    }

    @GetMapping("/ipInfo")
    public UnifyResponse getIpInfo(IpInfoParams ipInfoParams) {
        String baseUrl = "https://api.vvhan.com/api/getIpInfo";
        return RequestUtils.baseRequest(baseUrl, ipInfoParams);
    }

    @GetMapping("/loveTalk")
    public UnifyResponse getLoveTalk() {
        String baseUrl = "https://api.vvhan.com/api/love?type=json";
        return RequestUtils.baseRequest(baseUrl);
    }

    @GetMapping("/horoscope")
    public UnifyResponse getHoroscope(HoroscopeParams horoscopeParams) {
        String baseUrl = "https://api.vvhan.com/api/horoscope";
        return RequestUtils.baseRequest(baseUrl,horoscopeParams);
    }
}
