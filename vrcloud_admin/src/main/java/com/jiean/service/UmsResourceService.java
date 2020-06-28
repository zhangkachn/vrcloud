package com.jiean.service;

import com.jeian.vrcloud.mbg.model.UmsResource;

import java.util.List;

/**
 *后台资源管理Service
 * Created by zhangkang on 2020/6/22
 */
public interface UmsResourceService {



    List<UmsResource> listAll();

    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 添加后台资源
     */
    int create(UmsResource umsResource);

    /**
     *修改后台资源
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 根据id删除资源信息
     */
    int delete(Long id);
}


