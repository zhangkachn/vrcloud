package com.jiean.service;

import com.jeian.vrcloud.mbg.model.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源分类service
 * Created by zhangkang on 2020/6/22
 */
public interface UmsResourceCategoryService {


    /**
     * 查询所有后台资源分类
     * @return
     */
    List<UmsResourceCategory> listAll();
}
