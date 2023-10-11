package com.hcj.hmapiclientsdk;

import com.hcj.hmapiclientsdk.client.HmApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 **/
@Configuration
@ConfigurationProperties("hcj.client")
@Data
@ComponentScan
public class HmApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public HmApiClient HmApiClient(){
        return new HmApiClient(accessKey,secretKey);
    }
}
