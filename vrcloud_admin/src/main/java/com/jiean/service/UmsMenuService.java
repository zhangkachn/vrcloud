package com.jiean.service;

import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jiean.dto.UmsMenuNode;

import java.util.List;

/**
 * 后台菜单管理Service
 * Created by zhangkang on 2020/6/22
 */
public interface UmsMenuService {

    /*
     *树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();

    /**
     *分页查询菜单列表
     */
    List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize);
}
