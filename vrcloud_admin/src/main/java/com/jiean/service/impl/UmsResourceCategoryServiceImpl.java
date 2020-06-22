package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsResourceCategoryMapper;
import com.jeian.vrcloud.mbg.model.UmsResourceCategory;
import com.jeian.vrcloud.mbg.model.UmsResourceCategoryExample;
import com.jiean.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangkang on 2020/6/22
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        UmsResourceCategoryExample umsResourceCategoryExample = new UmsResourceCategoryExample();
        return resourceCategoryMapper.selectByExample(umsResourceCategoryExample);
    }
}
