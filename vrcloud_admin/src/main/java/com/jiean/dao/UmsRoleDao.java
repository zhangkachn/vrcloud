package com.jiean.dao;

import com.jeian.vrcloud.mbg.model.UmsMenu;
import org.apache.ibatis.annotations.Param;

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
}
