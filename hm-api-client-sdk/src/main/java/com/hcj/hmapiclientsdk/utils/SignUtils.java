package com.hcj.hmapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Map;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 **/
public class SignUtils {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey){
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return sha256.digestHex(content);
    }
}
