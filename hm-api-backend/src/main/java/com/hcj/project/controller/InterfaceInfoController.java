package com.hcj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.hcj.hmapi.common.model.entity.InterfaceInfo;
import com.hcj.hmapiclientsdk.client.HmApiClient;
import com.hcj.hmapiclientsdk.model.hmapiclient.Identification;
import com.hcj.hmapiclientsdk.model.request.UnifyRequest;
import com.hcj.hmapiclientsdk.service.HmApiService;
import com.hcj.project.common.*;
import com.hcj.project.constant.CommonConstant;
import com.hcj.project.exception.BusinessException;

import com.hcj.project.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.hcj.project.model.dto.interfaceinfo.InterfaceInfoSearchTextRequest;
import com.hcj.project.model.dto.interfaceinfo.InvokeRequest;
import com.hcj.project.model.enums.InterfaceStatusEnum;
import com.hcj.project.model.vo.UserVO;
import com.hcj.project.service.InterfaceInfoService;
import com.hcj.project.service.UserService;
import com.hcj.project.utils.JsonToMapUtils;
import com.hcj.project.utils.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Character.toUpperCase;

/**
 * 接口管理
 *
 * @author hcj
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    @Resource
    private HmApiService hmApiService;

    @Resource
    private HmApiClient hmApiClient;

    private final String GET_METHOD = "get";

    private final Gson gson = new Gson();
    /**
     * 分页获取列表
     * @param interfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        String description = interfaceInfoQuery.getDescription();
        // description 需支持模糊搜索
        interfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(interfaceInfoPage);
    }

    /**
     * 调用接口
     * @param invokeRequest id请求
     * @param request       请求
     * @return {@link BaseResponse}<{@link Object}>
     */
    @PostMapping("/invoke")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Object> invokeInterface(@RequestBody InvokeRequest invokeRequest, HttpServletRequest request) {
        if (ObjectUtils.anyNull(invokeRequest, invokeRequest.getId()) || invokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取登录用户
        UserVO loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        // 接口校验
        Long id = invokeRequest.getId();
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (interfaceInfo.getStatus() != InterfaceStatusEnum.ONLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口未开启");
        }
        // todo 接口必须参数校验

        // 构建请求参数
        List<InvokeRequest.Field> fieldList = invokeRequest.getRequestParams();
        String requestParams = "{}";
        if (fieldList != null && fieldList.size() > 0) {
            JsonObject jsonObject = new JsonObject();
            for (InvokeRequest.Field field : fieldList) {
                jsonObject.addProperty(field.getFieldName(), field.getValue());
            }
            requestParams = gson.toJson(jsonObject);
        }
        Map<String, Object> params = JsonToMapUtils.stringToMap(requestParams);
        try {
            UnifyRequest unifyRequest =
                    ParamUtils.getUnifyRequest(interfaceInfo.getUrl(), interfaceInfo.getMethod(), params);
            // 调用统一请求 sdk中不在校验参数，使用统一请求，参数校验交给调用sdk发起的一方和使用sdk接受请求的一方
            String data = hmApiService.doUnifyRequest(hmApiClient,unifyRequest);
            return ResultUtils.success(JsonToMapUtils.stringToMap(data));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * 根据 id 获取
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        return ResultUtils.success(interfaceInfo);
    }


    /**
     * 按搜索文本页查询数据
     *
     * @param interfaceInfoQueryRequest 接口信息查询请求
     * @param request                   请求
     * @return {@link BaseResponse}<{@link Page}<{@link InterfaceInfo}>>
     */
    @GetMapping("/get/searchText")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoBySearchTextPage(InterfaceInfoSearchTextRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);

        String searchText = interfaceInfoQueryRequest.getSearchText();
        long size = interfaceInfoQueryRequest.getPageSize();
        long current = interfaceInfoQueryRequest.getCurrent();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(searchText), "name", searchText)
                .or()
                .like(StringUtils.isNotBlank(searchText), "description", searchText);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        // 不是管理员只能查看已经上线的
        if (!userService.isAdmin(request)) {
            List<InterfaceInfo> interfaceInfoList = interfaceInfoPage.getRecords().stream()
                    .filter(interfaceInfo ->
                            interfaceInfo.getStatus().equals(InterfaceStatusEnum.ONLINE.getValue()))
                    .collect(Collectors.toList());
            interfaceInfoPage.setRecords(interfaceInfoList);
        }
        return ResultUtils.success(interfaceInfoPage);
    }




    private String getFunctionName(String method,String url){
        int lastIndexOf = url.lastIndexOf("/");
        String path = url.substring(lastIndexOf + 1);
        char[] chars = path.toCharArray();
        chars[0] = toUpperCase(chars[0]);
        return method+ String.valueOf(chars);
    }
}
