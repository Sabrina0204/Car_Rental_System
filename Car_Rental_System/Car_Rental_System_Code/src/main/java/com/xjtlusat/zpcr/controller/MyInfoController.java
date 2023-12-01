package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.entity.User;
import com.xjtlusat.zpcr.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("me")
public class MyInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping
    public String me(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", userInfoService.selectUserByUsername(user.getUsername()));
        return "me";
    }

    @RequestMapping("edit")
    public String userEdit(User user) {
        return userInfoService.userUpdate(user);
    }

}
