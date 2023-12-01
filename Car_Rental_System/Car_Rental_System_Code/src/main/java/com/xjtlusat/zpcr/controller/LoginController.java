package com.xjtlusat.zpcr.controller;

import com.xjtlusat.zpcr.entity.User;
import com.xjtlusat.zpcr.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping("login")
public class LoginController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberme", required = false) String isRemember,
                        Model model, HttpServletRequest request,HttpServletResponse response) {
        User user = userInfoService.selectUserByUsername(username);
        if(user == null) {
            model.addAttribute("error", "username or password error");
            return "login";
        }

        String dbPassword = user.getPassword();
        if(!dbPassword.equals(password)) {
            return "login";
        }

//        model.addAttribute("user", user);
        request.getSession().setAttribute("user", user);
        if (Objects.equals(isRemember, "remember-me")) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return "redirect:/cars";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/login";
    }

    @RequestMapping
    public String loginPage() {

        return "login";
    }

}
