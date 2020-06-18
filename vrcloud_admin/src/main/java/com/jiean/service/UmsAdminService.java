package com.jiean.service;

import com.jeian.vrcloud.mbg.model.UmsAdmin;
import com.jeian.vrcloud.mbg.model.UmsAdminLoginParam;
import com.jeian.vrcloud.mbg.model.UmsAdminParam;
import com.jeian.vrcloud.mbg.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by zhangkang on 2020/6/18
 */
public interface UmsAdminService {
    /**
     *注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);


    /**
     *根据用户名获取登录信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     *登录返回token
     */
    String login(UmsAdminLoginParam umsAdminLoginParam);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);
}
