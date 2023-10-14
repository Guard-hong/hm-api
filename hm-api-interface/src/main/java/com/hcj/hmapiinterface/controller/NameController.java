package com.hcj.hmapiinterface.controller;

import com.hcj.hmapiinterface.model.Test;
import com.hcj.hmapiinterface.model.User;
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
        Test test = new Test(user.getUsername(), "1232", "432@xx.com");
        return test.toString();
    }
}
