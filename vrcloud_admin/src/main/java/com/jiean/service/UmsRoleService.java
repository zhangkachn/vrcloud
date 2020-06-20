package com.jiean.service;

import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jeian.vrcloud.mbg.model.UmsRole;

import java.util.List;

/**
 * 角色后台service
 * Created by zhangkang on 2020/6/19
 */
public interface UmsRoleService {




    /**
     * 查询菜单权限
     * @param id
     * @return
     */
    List<UmsMenu> getMenuList(Long id);


    /**
     * 获取所有角色
     * @return
     */
    List<UmsRole> list();
}
