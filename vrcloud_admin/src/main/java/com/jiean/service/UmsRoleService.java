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


    /**
     *分页查询角色数据
     */
    List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum);

    /**
     *添加角色
     */
    int create(UmsRole role);

    /**
     * 获取角色相关菜单
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     *
     */
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    int allocResource(Long roleId, List<Long> resourceIds);
}
