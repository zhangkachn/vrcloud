package com.jiean.dao;

import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jeian.vrcloud.mbg.model.UmsRoleMenuRelation;
import com.jeian.vrcloud.mbg.model.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangkang on 2020/6/20
 * 自定义角色管理dao
 */
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);


    /**
     * 获取角色相关菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    int insertRoleMenuRelation(@Param("list") ArrayList<UmsRoleMenuRelation> umsRoleMenuRelationList);

    /**
     *重新关联角色资源中间表
     */
    int insertRoleesourceRelation(UmsRoleResourceRelation umsRoleResourceRelation);
}
