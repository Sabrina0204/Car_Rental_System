package com.xjtlusat.zpcr.controller;


import com.xjtlusat.zpcr.entity.User;
import com.xjtlusat.zpcr.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("users") // 路由
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping
    public ModelAndView userPage(){
        return new ModelAndView("redirect:/users/selectUser");
    }

    @RequestMapping("/addUser") // 定义接口, post用于新增
    public String save(User user) { // 将json文件映射为实体对象;问号表示任何对象
        return userInfoService.insert(user);
    }

    @RequestMapping("/selectUser")// get用于查询
    public String findPage( @RequestParam(defaultValue = "1") Integer pageNumber,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "") String search,
                            Model model) { // 将json文件映射为实体对象;问号表示任何对象
        return userInfoService.selectPage(pageNumber, pageSize, search, model);
    }

    @RequestMapping("/editUser") // put用于修改
    public String edit(User user) { // 将json文件映射为实体对象;问号表示任何对象
        return userInfoService.updateById(user);
    }

    @GetMapping ("/delete") // delete用于删除
    public String delete(@RequestParam Long id) { // 将json文件映射为实体对象;问号表示任何对象
        return userInfoService.deleteById(id);
    }

}
