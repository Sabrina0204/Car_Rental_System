package com.xjtlusat.zpcr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.entity.User;
import org.springframework.ui.Model;

public interface UserInfoService {
    User selectByNameAndPass(String username, String password);

    User selectUserByUsername(String username);

    User selectById(int id);
    String insert(User user);
    String updateById(User user);
    public String userUpdate(User user);
    String deleteById(Long id);
    String selectPage(Integer pageNumber, Integer pageSize, String search, Model model);
    Page<User> getPage(Integer pageNumber, Integer pageSize, String search);

    User selectByName(String username);
}
