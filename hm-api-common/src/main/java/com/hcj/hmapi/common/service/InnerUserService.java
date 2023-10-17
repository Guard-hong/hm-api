package com.hcj.hmapi.common.service;


import com.hcj.hmapi.common.model.vo.UserVO;
/**
 * 用户服务
 *
 * @author hcj
 */
public interface InnerUserService{

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    UserVO getInvokeUser(String accessKey);
}
