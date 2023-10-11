package com.hcj.hmapiinterface;

import com.hcj.hmapiclientsdk.client.HmApiClient;
import com.hcj.hmapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HmApiInterfaceApplicationTests {

    @Resource
    private HmApiClient hmApiClient;

    @Test
    void contextLoads() {
        User user = new User();
//        user.setUserName("hcj");
        String nameByPost = hmApiClient.getNameByPost(user);
        System.out.println(nameByPost);
//        String hcj = hm-apiClient.getNameByGet("hcj");
//        System.out.println(hcj);
    }

}
