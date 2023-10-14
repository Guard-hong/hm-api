package com.hcj.hmapiclientsdk.config;

import com.hcj.hmapiclientsdk.client.HmApiClient;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
@Data
@Configuration
@ConfigurationProperties("hm.api.client")
@ComponentScan
public class HmApiClientConfig {
    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 秘密密钥
     */
    private String secretKey;

    @Bean
    public HmApiClient hmApiClient() {
        return new HmApiClient();
    }
}
