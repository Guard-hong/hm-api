package com.hcj.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hcj.hmapi.common.model.entity.UserInterfaceInvoke;
import com.hcj.project.common.ErrorCode;
import com.hcj.project.exception.BusinessException;
import com.hcj.project.mapper.UserInterfaceInvokeMapper;
import com.hcj.project.service.InterfaceInfoService;
import com.hcj.project.service.UserInterfaceInvokeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户接口信息服务实现类
 *
 * @Author:HCJ
 * @DateTime:2023/10/2
 * @Description:
 */
@Service
public class UserInterfaceInvokeServiceImpl extends ServiceImpl<UserInterfaceInvokeMapper, UserInterfaceInvoke>
    implements UserInterfaceInvokeService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public boolean invoke(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInvoke> qw = new QueryWrapper<>();
        qw.eq("interfaceId", interfaceInfoId).eq("userId", userId);
        UserInterfaceInvoke userInterfaceInfo = getOne(qw);
        // 没有调用记录， 创建一条记录
        if(userInterfaceInfo == null){
            userInterfaceInfo = new UserInterfaceInvoke();
            userInterfaceInfo.setUserId(userId);
            userInterfaceInfo.setInterfaceId(interfaceInfoId);
            userInterfaceInfo.setTotalInvokes(1L);
            save(userInterfaceInfo);
        }else {
            UpdateWrapper<UserInterfaceInvoke> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("interfaceId", interfaceInfoId);
            updateWrapper.eq("userId", userId);
            updateWrapper.setSql("totalInvokes = totalInvokes+1");
            update(updateWrapper);
        }
        // 更新接口总调用次数
        interfaceInfoService.updateTotalInvokes(interfaceInfoId);
        return true;
    }

}




