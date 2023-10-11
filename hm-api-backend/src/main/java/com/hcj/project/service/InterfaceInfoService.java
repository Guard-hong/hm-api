package com.hcj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcj.hmapi.common.model.entity.InterfaceInfo;

/**
 * 接口信息服务
 *
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
