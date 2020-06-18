package com.jiean.config;

import com.jiean.service.UmsAdminService;
import com.jiean.vrcloud.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Security;

/**
 * Created by zhangkang on 2020/6/18
 */
public class MallSecurityConfig extends SecurityConfig {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    //private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);        // 这里是数据的查询
    }

}
