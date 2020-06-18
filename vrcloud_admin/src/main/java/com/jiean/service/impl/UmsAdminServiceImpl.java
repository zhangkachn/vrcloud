package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsAdminMapper;
import com.jeian.vrcloud.mbg.model.*;
import com.jiean.service.UmsAdminCacheService;
import com.jiean.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangkang on 2020/6/18
 */
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;


    public UmsAdmin getAdminByUsername(String username) {
        //获取缓存中的数据
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) {
            return admin;
        }
        // Redis中没有数据，获取数据存储在redis中
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins != null && umsAdmins.size() > 0) {
            UmsAdmin umsAdmin = umsAdmins.get(0);
            adminCacheService.setAdmin(umsAdmin);
            return umsAdmin;
        }
        return null;
    }


    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        adminCacheService.getResourceList(adminId);

        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
        if (umsAdmins.size() > 0) {
            return null;
        }
        //密码加密
        String encode = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encode);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(UmsAdminLoginParam umsAdminLoginParam) {
        // c
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        //从Redis获取用户数据
        UmsAdmin adminByUsername = getAdminByUsername(username);
        if (adminByUsername != null) {
            List<UmsResource> resourceList = getResourceList(adminByUsername.getId());
        }


        return null;
    }


}
