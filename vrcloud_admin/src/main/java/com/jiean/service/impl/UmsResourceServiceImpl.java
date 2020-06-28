package com.jiean.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.jeian.vrcloud.mbg.mapper.UmsResourceMapper;
import com.jeian.vrcloud.mbg.model.UmsResource;
import com.jeian.vrcloud.mbg.model.UmsResourceExample;
import com.jiean.service.UmsResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangkang on 2020/6/22
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        UmsResourceExample umsResourceExample = new UmsResourceExample();
        return resourceMapper.selectByExample(umsResourceExample);
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceExample umsResourceExample = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = umsResourceExample.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andUrlLike('%'+urlKeyword+'%');
        }
        List<UmsResource> resourceList = resourceMapper.selectByExample(umsResourceExample);
        return resourceList;
    }

    @Override
    public int create(UmsResource umsResource) {
        int count = resourceMapper.insert(umsResource);
        return count;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        return count;
    }
}
