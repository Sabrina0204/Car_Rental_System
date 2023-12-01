package com.xjtlusat.zpcr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjtlusat.zpcr.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
