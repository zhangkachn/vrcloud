package com.jiean.service;

import com.jeian.vrcloud.mbg.model.*;
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

    /**
     *获取用户信息
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     *根据用户名或姓名分页获取用户列表
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin umsAdmin);

    /**
     * 删除指定用户信息
     */
    int delete(Long id);

    /**
     *获取指定用户的角色
     */
    List<UmsRole> getRoleList(Long adminId);


    /**
     * 给用户分配角色
     */
    int updateRole(Long adminId, List<Long> roleIds);
}
