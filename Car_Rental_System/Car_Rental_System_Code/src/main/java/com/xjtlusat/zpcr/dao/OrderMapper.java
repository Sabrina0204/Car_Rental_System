package com.xjtlusat.zpcr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjtlusat.zpcr.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
