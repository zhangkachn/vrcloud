package com.jiean.controller;

import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jiean.dto.UmsMenuNode;
import com.jiean.service.UmsMenuService;
import com.jiean.vrcloud.common.api.CommonPage;
import com.jiean.vrcloud.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangkang on 2020/6/22
 */
@Controller
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
       List<UmsMenu>  menusList= menuService.list(parentId,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(menusList));
    }




    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> menuNodes = menuService.treeList();
        return CommonResult.success(menuNodes);
    }

}
