package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsAdminLoginLogMapper;
import com.jeian.vrcloud.mbg.mapper.UmsAdminMapper;
import com.jeian.vrcloud.mbg.mapper.UmsAdminRoleRelationMapper;
import com.jeian.vrcloud.mbg.model.*;
import com.jiean.bo.AdminUserDetails;
import com.jiean.dao.UmsAdminRoleRelationDao;
import com.jiean.service.UmsAdminCacheService;
import com.jiean.service.UmsAdminService;
import com.jiean.vrcloud.security.util.JwtTokenUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangkang on 2020/6/18
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;


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
        // redis获取请求资源值
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if (resourceList != null) {
            return resourceList;
        }
        //redis中没有值 获取值设置到Redis中
        List<UmsResource> resourceListRedis = umsAdminRoleRelationDao.getResourceList(adminId);
        adminCacheService.setResourceList(adminId, resourceListRedis);
        return resourceListRedis;
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
        String token = null;
        String username = umsAdminLoginParam.getUsername();
        String password = umsAdminLoginParam.getPassword();
        UserDetails userDetails = loadUserByUsername(username);
        // userDetails.getPassword()是加密后的数据
        // 传入的密码和数据库中注册加密后的密码通过密码编辑器passwordEncoder比较
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // userDetails.getAuthorities() 用户的资源
        // 得到用户信息的上下文（角色等信息）
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //为用户建立一个安全上下文
        //用户接下来可能执行一些权限访问机制下的受保护的操作，检查与当前安全上下文有关的必须的权限
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtil.generateToken(userDetails);
        //insertLoginLog(username);
        return token;
    }

    /**
     * 添加登录记录
     *
     * @param username
     */
    private void insertLoginLog(String username) {
        UmsAdmin adminByUsername = getAdminByUsername(username);
        if (adminByUsername == null)
            return;
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setCreateTime(new Date());
        // 从springsecurityconder上下文获取IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        umsAdminLoginLog.setIp(request.getRemoteAddr());
        umsAdminLoginLog.setAdminId(adminByUsername.getId());
        umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //从Redis获取用户数据
        UmsAdmin adminByUsername = getAdminByUsername(username);
        if (adminByUsername != null) {
            // 资源数据
            List<UmsResource> resourceList = getResourceList(adminByUsername.getId());
            return new AdminUserDetails(adminByUsername, resourceList);
        }
        return (UserDetails) new UsernameNotFoundException("用户名或者密码错误");
    }


}
