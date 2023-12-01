package com.xjtlusat.zpcr.service.implement;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.dao.UserMapper;
import com.xjtlusat.zpcr.entity.User;
import com.xjtlusat.zpcr.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByNameAndPass(String username, String password) {
        return userMapper.selectByNameAndPass(username, password);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public String insert(User user) {
        userMapper.insert(user);
        return "redirect:/users/selectUser";
    }

    @Override
    public String updateById(User user) {
        userMapper.updateById(user);
        return "redirect:/users/selectUser";
    }
    @Override
    public String userUpdate(User user) {
        userMapper.updateById(user);
        return "redirect:/me";
    }

    @Override
    public String deleteById(Long id) {
        userMapper.deleteById(id);
        return "redirect:/users/selectUser";
    }

    @Override
    public String selectPage(Integer pageNumber, Integer pageSize, String search, Model model) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)) { // 防止该字段在数据库中未设置
            wrapper.like(User::getUsername, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        model.addAttribute("data", userPage);
        return "users";
    }


    @Override
    public Page<User> getPage(Integer pageNumber, Integer pageSize, String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)) { // 防止该字段在数据库中未设置
            wrapper.like(User::getUsername, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        return userPage;
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

}
