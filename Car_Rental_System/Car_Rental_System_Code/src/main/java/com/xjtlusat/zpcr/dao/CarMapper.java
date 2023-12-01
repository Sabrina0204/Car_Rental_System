package com.xjtlusat.zpcr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjtlusat.zpcr.entity.Car;
import com.xjtlusat.zpcr.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CarMapper extends BaseMapper<Car> {
    Page<Car> findPage(Page<Car> page, @Param("search") String search);

    @Select("select id from car where license_plate_number = #{num}")
    Integer getCarIDByLicensePlateNumber(String num);

}
