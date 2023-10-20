package com.hcj.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hcj.hmapi.common.model.entity.InterfaceInfo;
import com.hcj.project.common.ErrorCode;
import com.hcj.project.exception.BusinessException;
import com.hcj.project.mapper.InterfaceInfoMapper;
import com.hcj.project.service.InterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: hcj
 * @Date: 2023/10/08 08:52:13
 * @Version: 1.0
 * @Description: 接口信息服务impl
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {

    @Override
    public boolean updateTotalInvokes(long interfaceId) {
        LambdaUpdateWrapper<InterfaceInfo> invokeLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        invokeLambdaUpdateWrapper.eq(InterfaceInfo::getId, interfaceId);
        invokeLambdaUpdateWrapper.setSql("totalInvokes = totalInvokes + 1");
        return this.update(invokeLambdaUpdateWrapper);
    }
}

