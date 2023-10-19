package com.hcj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcj.hmapi.common.model.entity.UserInterfaceInvoke;

/**
 * 用户接口信息服务
 *
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 */
public interface UserInterfaceInvokeService extends IService<UserInterfaceInvoke> {

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invoke(long interfaceInfoId, long userId);
}
