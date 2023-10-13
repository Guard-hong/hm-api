package com.hcj.hmapiclientsdk.aop;

import com.hcj.hmapiclientsdk.client.HmApiClient;
import com.hcj.hmapiclientsdk.common.ErrorCode;
import com.hcj.hmapiclientsdk.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

/**
 * 请求头拦截: 校验 accessKey 和 secretKey
 * @Author:HCJ
 * @DateTime:2023/10/13
 * @Description:
 **/
@Aspect
@Component
public class HeaderInterceptor {
    @Resource
    private HmApiClient hmApiClient;

    @Around("execution(* com.hcj.hmapiclientsdk.service.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        if(!StringUtils.isAllBlank(hmApiClient.getAccessKey(),hmApiClient.getSecretKey())){
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR,"accessKey或secretKey为空");
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}
