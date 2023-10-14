package com.hcj.hmapiinterface;

import com.hcj.hmapiclientsdk.client.HmApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HmApiInterfaceApplicationTests {

    @Resource
    private HmApiClient hmApiClient;

    @Test
    void contextLoads() {
    }

}
