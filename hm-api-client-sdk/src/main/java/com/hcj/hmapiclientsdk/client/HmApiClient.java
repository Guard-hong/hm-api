package com.hcj.hmapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hcj.hmapiclientsdk.model.User;
import com.hcj.hmapiclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 **/
public class HmApiClient {
    private static final String GATEWAY_HOST = "http://localhost:8090";
    private String accessKey;
    private String secretKey;

    public HmApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        // 可以当度传入http参数，这样参数会自动做url编码，拼接在url中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name){
        // 可以当度传入http参数，这样参数会自动做url编码，拼接在url中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }



    private Map<String,String> getHeaderMap(String body){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.genSign(body,secretKey));
        return hashMap;
    }

    public String getNameByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
