package com.hcj.hmapiinterface.controller;

import cn.hutool.json.JSONUtil;
import com.hcj.hmapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hcj.hmapiinterface.utils.RequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(){
        String url = "https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json";
        String res = RequestUtils.get(url);
        PoisonousChickenSoupResponse poisonousChickenSoupResponse =
                JSONUtil.toBean(res, PoisonousChickenSoupResponse.class);
        return poisonousChickenSoupResponse;
    }
}
