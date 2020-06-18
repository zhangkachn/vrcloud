package com.jiean.controller;

import com.jeian.vrcloud.mbg.model.UmsAdmin;
import com.jeian.vrcloud.mbg.model.UmsAdminLoginParam;
import com.jeian.vrcloud.mbg.model.UmsAdminParam;
import com.jiean.service.UmsAdminService;
import com.jiean.vrcloud.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangkang on 2020/6/18
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "用户登录返回token")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CommonResult<UmsAdmin> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam) {
         String token =umsAdminService.login(umsAdminLoginParam);
     return null;
    }


    /*@ApiOperation(value = "获取用户详细信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam){
        UmsAdmin umsAdmin =umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }*/




}
