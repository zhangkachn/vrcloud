package com.jiean.dao;

import com.jeian.vrcloud.mbg.model.UmsAdminRoleRelation;
import com.jeian.vrcloud.mbg.model.UmsResource;
import com.jeian.vrcloud.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 *自定义后台用户与角色管理Dao，资源关系，设计到表与表之间的联合查询，需要自定定义xml文件与之对应
 * Created by zhangkang on 2020/6/19
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);


    /*
    获取用户的角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);


    int insertList(@Param("list") ArrayList<UmsAdminRoleRelation> adminRoleRelations);
}
