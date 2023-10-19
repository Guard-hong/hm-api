package com.hcj.project.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hcj
 * @Date: 2023/10/10 10:33:34
 * @Version: 1.0
 * @Description: 用户绑定电子邮件请求
 */
@Data
public class UserBindEmailRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String emailAccount;

    private String captcha;
}
