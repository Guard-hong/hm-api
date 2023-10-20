package com.hcj.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hcj.hmapi.common.model.entity.InterfaceInfo;

/**
 * @Author: hcj
 * @Date: 2023/10/15 09:32:11
 * @Version: 1.0
 * @Description: 接口信息服务
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {


    /**
     * 更新总调用数
     *
     * @param interfaceId 接口id
     * @return boolean
     */
    boolean updateTotalInvokes(long interfaceId);
}
