package com.xjtlusat.zpcr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> { // BaseMapper实现了基本的单表增删改查
    // 一对多查询
    Page<User> findPage(Page<User> page, @Param("nickName") String nickName);

//    @Select("select count(id) count, address from user GROUP BY address")
//    List<UserAddressDto> countAddress();

    // 查询用户名
    @Select("select * from user where username=#{username} and password=#{password}")
    User selectByNameAndPass(String username, String password);

    @Update("update user set password = #{newPassword} where id = #{userId}")
    int updatePassword(Map<String, Object> map);

    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    @Select("select * from user where username=#{username}")
    User selectByName(String username);

}