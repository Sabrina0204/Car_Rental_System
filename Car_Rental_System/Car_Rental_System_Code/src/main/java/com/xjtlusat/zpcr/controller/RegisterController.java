package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.dao.UserMapper;
import com.xjtlusat.zpcr.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @RequestMapping("/register/register")
    public ModelAndView addUser(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("confirmedPassword") String confirmedPassword,
                                @RequestParam("address") String address,
                                Model model, HttpServletRequest request) {
        if(username.length()!=11){
            model.addAttribute("error","the length of username must be 11");
            return new ModelAndView("register");
        }
        User user = userMapper.selectByUsername(username);
        if(user!=null){
            model.addAttribute("error","the username has been registered");
            return new ModelAndView("register");
        }
        if(password.length()<8 || confirmedPassword.length()<8){
            model.addAttribute("error","the length of password must be greater than 8");
            return new ModelAndView("register");
        }
        if (!password.equals(confirmedPassword)) {
            model.addAttribute("error","the password and confirmedPassword must be same");
            return new ModelAndView("register");
        }
        /// TODO: 2023/4/14 如果还有验证码 还要进行验证码的判断

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(username);
        user.setRole("user");
        user.setAddress(address);
        userMapper.insert(user);
        return new ModelAndView("redirect:/login");
    }
}
