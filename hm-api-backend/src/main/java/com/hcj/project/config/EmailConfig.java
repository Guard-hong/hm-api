package com.hcj.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hcj
 * @Date: 2023/10/10 12:21:53
 * @Version: 1.0
 * @Description: 电子邮件配置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class EmailConfig {
    private String emailFrom;
}
