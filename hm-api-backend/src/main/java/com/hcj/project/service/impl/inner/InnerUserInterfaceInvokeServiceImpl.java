package com.hcj.project.service.impl.inner;


import com.hcj.hmapi.common.service.InnerUserInterfaceInvokeService;
import com.hcj.project.service.UserInterfaceInvokeService;
import com.hcj.project.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部用户接口信息服务实现类
 *
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 */
@DubboService
public class InnerUserInterfaceInvokeServiceImpl implements InnerUserInterfaceInvokeService {

    @Resource
    private UserInterfaceInvokeService userInterfaceInvokeService;


    @Override
    public boolean invoke(long interfaceInfoId, long userId) {
        return userInterfaceInvokeService.invoke(interfaceInfoId, userId);
    }
}
