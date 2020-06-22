package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsResourceMapper;
import com.jeian.vrcloud.mbg.model.UmsResource;
import com.jeian.vrcloud.mbg.model.UmsResourceExample;
import com.jiean.service.UmsResourceService;
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
}
