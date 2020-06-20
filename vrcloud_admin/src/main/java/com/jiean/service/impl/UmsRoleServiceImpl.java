package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsMenuMapper;
import com.jeian.vrcloud.mbg.mapper.UmsRoleMapper;
import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jeian.vrcloud.mbg.model.UmsRole;
import com.jeian.vrcloud.mbg.model.UmsRoleExample;
import com.jiean.dao.UmsRoleDao;
import com.jiean.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
