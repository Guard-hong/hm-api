package com.hcj.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcj.hmapi.common.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 洪
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-10-05 23:01:24
* @Entity com.hcj.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




