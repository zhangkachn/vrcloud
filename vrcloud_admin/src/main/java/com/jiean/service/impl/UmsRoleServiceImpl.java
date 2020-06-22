package com.jiean.service.impl;

import com.github.pagehelper.PageHelper;
import com.jeian.vrcloud.mbg.mapper.UmsMenuMapper;
import com.jeian.vrcloud.mbg.mapper.UmsRoleMapper;
import com.jeian.vrcloud.mbg.mapper.UmsRoleMenuRelationMapper;
import com.jeian.vrcloud.mbg.mapper.UmsRoleResourceRelationMapper;
import com.jeian.vrcloud.mbg.model.*;
import com.jiean.dao.UmsRoleDao;
import com.jiean.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangkang on 2020/6/19
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleDao umsRoleDao;
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper resourceRelationMapper;



    @Override
    public List<UmsMenu> getMenuList(Long id) {
        List<UmsMenu> menuList = umsRoleDao.getMenuList(id);
        return menuList;
    }

    @Override
    public List<UmsRole> list() {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        List<UmsRole> roleList = roleMapper.selectByExample(umsRoleExample);
        return roleList;
    }

    @Override
    public List<UmsRole> listPage(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            umsRoleExample.createCriteria().andNameLike("%" + keyword + "%");
        }
        List<UmsRole> roleList = roleMapper.selectByExample(umsRoleExample);
        return roleList;
    }

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return  umsRoleDao.getMenuListByRoleId(roleId);

    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 先删除原来的关系
        // 根据Id进行删除
        UmsRoleMenuRelationExample umsRoleMenuRelationExample = new UmsRoleMenuRelationExample();
        UmsRoleMenuRelationExample.Criteria criteria = umsRoleMenuRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(umsRoleMenuRelationExample);
        // 建立新的关系 ，向角色 菜单中间表中插入数据，可以使用mybatisgenertior生成的向里转入
        // 也可以使用自定义的foreach sql来插入数据，个人认为单条插入数据相对较好
       /* for (Long menuId:menuIds) {
            UmsRoleMenuRelation umsRoleMenuRelation = new UmsRoleMenuRelation();
            umsRoleMenuRelation.setRoleId(roleId);
            umsRoleMenuRelation.setMenuId(menuId);
            roleMenuRelationMapper.insert(umsRoleMenuRelation);
        }*/

        // 通过自定义SQL
        ArrayList<UmsRoleMenuRelation> UmsRoleMenuRelationList = new ArrayList<>();
        for (Long menuId:menuIds) {
            UmsRoleMenuRelation umsRoleMenuRelation = new UmsRoleMenuRelation();
            umsRoleMenuRelation.setRoleId(roleId);
            umsRoleMenuRelation.setMenuId(menuId);
            UmsRoleMenuRelationList.add(umsRoleMenuRelation);
        }
       int count= umsRoleDao.insertRoleMenuRelation(UmsRoleMenuRelationList);
        return count;
        //return menuIds.size();
    }
    // TDO
    // 给角色分配资源同给角色分配菜单操作流程一致，都是操作中间表，可以使用mybatisgener生成的文件，还可以使用
    // 自定义SQL 自定义SQL优化较好 一条SQL语句执行插入多条数据
    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
         //需要删除原来的关系
        UmsRoleResourceRelationExample umsRoleResourceRelationExample = new UmsRoleResourceRelationExample();
        UmsRoleResourceRelationExample.Criteria criteria = umsRoleResourceRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        resourceRelationMapper.deleteByExample(umsRoleResourceRelationExample);
        //删除后需要关联新的关系
        ArrayList<UmsRoleResourceRelation> roleResourceRelationList = new ArrayList<>();

        for (Long resourceId:resourceIds) {
            UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();
            umsRoleResourceRelation.setResourceId(resourceId);
            umsRoleResourceRelation.setRoleId(roleId);
            roleResourceRelationList.add(umsRoleResourceRelation);
            int  count =umsRoleDao.insertRoleesourceRelation(umsRoleResourceRelation);
        }
        return 12;
    }
}
