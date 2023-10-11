package com.hcj.hmapiinterface.controller;

import com.hcj.hmapiclientsdk.model.User;
import com.hcj.hmapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 *
 * 名称 API
 **/
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet(String name){
        return "GET 你的名字是"+name;
    }

    @PostMapping("/post")
    public String getNameByPost(String name){
        return "POST 你的名字是"+name;
    }

    @PostMapping("/user")
    public String getNameByPost(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        String result =  "POST 你的名字是"+user.getUsername();
        return result;
    }
}
