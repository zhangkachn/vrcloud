package com.jiean.service.impl;

import com.jeian.vrcloud.mbg.mapper.UmsMenuMapper;
import com.jeian.vrcloud.mbg.model.UmsMenu;
import com.jeian.vrcloud.mbg.model.UmsMenuExample;
import com.jiean.dto.UmsMenuNode;
import com.jiean.service.UmsMenuService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangkang on 2020/6/22
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
    public List<UmsMenuNode> treeList() {
        UmsMenuExample umsMenuExample = new UmsMenuExample();
        List<UmsMenu> umsMenus = menuMapper.selectByExample(umsMenuExample);
        List<UmsMenuNode> result = umsMenus.stream()
                .filter(menu -> menu.getParentId().equals(0L))// 返回一次流
                .map(menu -> covertMenuNode(menu, umsMenus)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     * menu是过滤后的流数据
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))  // 这里可能有多层结构，所以循环调用covertMenuNode
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

}
